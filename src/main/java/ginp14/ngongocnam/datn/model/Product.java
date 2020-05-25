package ginp14.ngongocnam.datn.model;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(name = "product_name")
    @NotBlank(message = "Product name cannot be blank")
    private String name;

    @Column(name = "product_images_url")
    private String url;

    @Column(name = "product_price", columnDefinition = "double")
    @DecimalMin(value = "1", message = "Min price is $1")
    private double price;

    @Column(name = "description")
    private String description;

    @Column(name = "sold")
    private int sold;

    @Column(name = "discount")
    private int discount;


    @Column(name = "status", columnDefinition = "tinyint(1)")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "cat_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    @Column(name = "created_at")
    @UpdateTimestamp
    private Timestamp created_at;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updated_at;


    public Product() {
    }

    public Product(int id, @NotBlank(message = "Product name cannot be blank") String name, String url, @DecimalMin(value = "1", message = "Min price is $1") double price, String description, int sold, int discount, boolean status, Category category, Type type, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.price = price;
        this.description = description;
        this.sold = sold;
        this.discount = discount;
        this.status = status;
        this.category = category;
        this.type = type;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
