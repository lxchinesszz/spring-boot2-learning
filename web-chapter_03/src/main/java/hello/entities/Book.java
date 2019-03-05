package hello.entities;

import java.util.Date;

/**
 * @author liuxin
 * @version Id: Book.java, v 0.1 2019-03-05 14:58
 */
public class Book {
  private String name;
  private String author;
  private Date date;
  private double price;
  private String saleType;

  public Book(String name, String author, Date date, double price, String saleType) {
    this.name = name;
    this.author = author;
    this.date = date;
    this.price = price;
    this.saleType = saleType;
  }


  public String getName() {
    return name;
  }

  public String getAuthor() {
    return author;
  }

  public Date getDate() {
    return date;
  }

  public double getPrice() {
    return price;
  }

  public String getSaleType() {
    return saleType;
  }
}
