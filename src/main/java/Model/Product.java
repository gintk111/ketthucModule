package Model;

public class Product {
    int id;
    String name;
    int amount;
    int cat_id;
    String cat_name;
    String description;
    String color;
    float price;

    public Product(int id, String name, int amount, int cat_id, String description, String color, float price) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.cat_id = cat_id;
        this.description = description;
        this.color = color;
        this.price = price;
    }

    public Product(int id, String name, int amount, String cat_name, String description, String color, float price) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.cat_name = cat_name;
        this.description = description;
        this.color = color;
        this.price = price;
    }

    public Product(int id, String name, int amount, int cat_id, String cat_name, String description, String color, float price) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.description = description;
        this.color = color;
        this.price = price;
    }

    public Product(String name, int amount, int cat_id, String description, String color, float price) {
        this.name = name;
        this.amount = amount;
        this.cat_id = cat_id;
        this.description = description;
        this.color = color;
        this.price = price;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
