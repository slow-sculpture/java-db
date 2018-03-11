package hibernate.shop.order;

import hibernate.shop.product.Price;
import hibernate.shop.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Embedded
    Price price;
    BigDecimal amount;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn
    Product product;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn
    Order order;
}
