package bean;

public class BookBean {

    private String bid, title, author, picture, description;
    private String category;
    private double price;
    private int quantitySold;

    public BookBean(String bid, String title, double price, String author,
                    String category, String picture, String description, int quantitySold) {
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

    public String getCategory() {
        return category;

    }

    public void setCategory(String category) {
        this.category = category;
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
