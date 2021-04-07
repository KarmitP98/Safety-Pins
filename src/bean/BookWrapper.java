package bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bookReport")
public class BookWrapper {
    @XmlAttribute(name = "bid")
    private String bid;

    @XmlAttribute(name = "title")
    private String title;
    @XmlAttribute(name = "author")
    private String author;

    @XmlAttribute(name = "picture")
    private String picture; //url

    @XmlAttribute(name = "description")
    private String description;

    @XmlAttribute(name = "category")
    private BookType category;
    @XmlAttribute(name = "price")
    private double price;
    @XmlAttribute(name = "quantitySold")
    private int quantitySold;


    public BookWrapper(String bid, String title, double price, String author,
                       BookType category, String picture, String description, int quantitySold) {
        super();
        this.bid = bid;
        this.title = title;
        this.author = author;
        this.category = category;
        this.picture = picture;
        this.description = description;
        this.price = price;
        this.quantitySold = quantitySold;
    }


    public String getBid() {
        return bid;
    }


    public void setBid(String bid) {
        this.bid = bid;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }


    public String getPicture() {
        return picture;
    }


    public void setPicture(String picture) {
        this.picture = picture;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public BookType getCategory() {
        return category;
    }


    public void setCategory(BookType category) {
        this.category = category;
    }


    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public int getQuantitySold() {
        return quantitySold;
    }


    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }


}
