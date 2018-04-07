package hibernate.shop.cart;


import hibernate.shop.product.Price;
import hibernate.shop.product.Product;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    Product product;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    Cart cart;
    BigDecimal amount;
    @Embedded
    Price price;
}

