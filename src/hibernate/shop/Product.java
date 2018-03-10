package hibernate.shop;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Product implements Serializable {

    @Id
    private Long id;
    private String name;

    public Product(){

    }
}
