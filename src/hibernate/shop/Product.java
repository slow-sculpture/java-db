package hibernate.shop;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;


@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate date;
    @Enumerated
    private ProductType productType;

    public Product(){

    }
}
