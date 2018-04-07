package hibernate.shop.cart;


import hibernate.shop.product.Price;
import hibernate.shop.product.Product;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class CartDetail implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Embedded
    Price price;
    int amount;
    @ManyToOne
    Cart cart;
//    @ManyToOne
//    Product product;


}
