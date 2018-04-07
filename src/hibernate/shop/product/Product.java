package hibernate.shop.product;

import hibernate.shop.cart.CartDetail;
import hibernate.shop.order.OrderDetail;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.*;


@Entity
@EqualsAndHashCode(exclude = "orderDetailSet")
//jakbysmy chcieli zmienic nazwe tabeli
//@Table("product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //za pomoca column mozna np zmienic nazwe, zdefiniowac jakiej dlugosci jest pole
    //@Column(name="data_dodania", length = 20;)
    private LocalDate date;
    @Enumerated
    private ProductType productType;

    @Embedded
    private Price price;

    //jeden produkt moze byc na wielu pozycjach zamowienia
    @OneToMany (mappedBy = "product")
    Set<OrderDetail> orderDetailSet;

//    @OneToMany
//    Set<CartDetail> cartDetailSet;

    //bezargumentowy konstruktor dla hibernate
    public Product(){

    }

    public Product(String name, ProductType productType, Price price) {
        this.name = name;
        this.productType = productType;
        this.price = price;
        this.date = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
