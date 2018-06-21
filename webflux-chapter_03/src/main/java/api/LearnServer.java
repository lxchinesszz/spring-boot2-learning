package api;

import io.undertow.Undertow;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.http.server.reactive.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.ipc.netty.http.server.HttpServer;
import javax.servlet.Servlet;

/**
 * 支持的服务器
 *
 * @author liuxin
 * @version Id: LearnServer.java, v 0.1 2018/6/21 上午10:49
 */
public class LearnServer {
    public static final String host = "127.0.0.1";
    public static final int port = 8081;
    private static final String URL = "/03/";

    public static void main(String[] args) {
        web_netty(host, port);
        web_undertow(host,port);
        web_jetty(host,port);

    }

    /**
     *
     * @param host 域名
     * @param port 端口
     * @param httpHandler http处理器
     */
    public static void web_netty(String host, int port,HttpHandler httpHandler) {
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
        HttpServer.create(host, port).newHandler(adapter).block();
        while (true) ;
    }

    /**
     * 构建一个使用Netty作为web容器的服务器
     *
     * @param host 域名
     * @param port 端口
     */
    private static void web_netty(String host, int port) {
        RouterFunction<ServerResponse> routerFunction = RouterFunctions.route(RequestPredicates.POST(URL),
                request ->
                        ServerResponse.ok().body(BodyInserters.fromObject("hello netty")));

        HttpHandler httpHandler = RouterFunctions.toHttpHandler(routerFunction);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
        HttpServer.create(host, port).newHandler(adapter).block();
        while (true) ;
    }

    /**
     * 构建一个使用undertow的web容器服务器
     *
     * @param host 域名
     * @param port 端口
     */
    private static void web_undertow(String host, int port) {
        RouterFunction<ServerResponse> routerFunction = RouterFunctions.route(RequestPredicates.POST(URL),
                request ->
                        ServerResponse.ok().body(BodyInserters.fromObject("hello undertow")));

        HttpHandler httpHandler = RouterFunctions.toHttpHandler(routerFunction);
        UndertowHttpHandlerAdapter adapter = new UndertowHttpHandlerAdapter(httpHandler);
        Undertow server = Undertow.builder().addHttpListener(port, host).setHandler(adapter).build();
        server.start();
        while (true) ;
    }


    /**
     * 构建一个使用tomcat的web容器服务器
     *
     * @param host 域名
     * @param port 端口
     */
    private static void web_jetty(String host, int port) {
        RouterFunction<ServerResponse> routerFunction = RouterFunctions.route(RequestPredicates.POST(URL),
                request ->
                        ServerResponse.ok().body(BodyInserters.fromObject("hello jetty")));

        HttpHandler httpHandler = RouterFunctions.toHttpHandler(routerFunction);
        Servlet servlet = new JettyHttpHandlerAdapter(httpHandler);
        try {
            Server server = new Server();
            ServletContextHandler contextHandler = new ServletContextHandler(server, "");
            contextHandler.addServlet(new ServletHolder(servlet), "/");
            contextHandler.start();

            ServerConnector connector = new ServerConnector(server);
            connector.setHost(host);
            connector.setPort(port);
            server.addConnector(connector);

            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) ;
    }


}
