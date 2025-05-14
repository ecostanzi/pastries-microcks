package it.ecostanzi.pastries;

public class Pastry {
    private String name;
    private String description;
    private String size;
    private Double price;
    private String status;

    public Pastry() {}

    public Pastry(String name, String description, String size, Double price, String status) {
        this.name = name;
        this.description = description;
        this.size = size;
        this.price = price;
        this.status = status;
    }

    // Getters and setters

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
