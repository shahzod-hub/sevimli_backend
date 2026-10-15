package coder.uz.uzum.entity.dto;

public class ProductDto {
    private String name;
    private Double price;
    private int quantity;
    private String image;

    public ProductDto() {
    }

    public ProductDto(String name, Double price, int quantity,String image) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", image='" + image + '\'' +
                '}';
    }
}
