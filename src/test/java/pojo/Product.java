package pojo;

public class Product {
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;


    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product(String title, double price, String image,String category,String description){
this.title=title;
this.price=price;
this.image=image;
this.category=category;
this.description=description;
    }
}
