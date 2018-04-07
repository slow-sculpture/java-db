package hibernate.shop.cart;


import hibernate.shop.product.Price;
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
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String email;
    @Embedded
    @Transient
    Price price;
    @OneToMany (mappedBy = "cartDetail")
    CartDetail cartDetail;


}
