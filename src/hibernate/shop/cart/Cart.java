package hibernate.shop.cart;


import hibernate.shop.product.Price;
import hibernate.shop.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = "cartDetailSet")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String email;
    @OneToOne
    User user;

    //    private Price price;
    //jeden cart moze byc na wielu pozycji koszyka
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    Set<CartDetail> cartDetailSet;

    public void addCartDetail(CartDetail cartDetail){
        cartDetail.setCart(this);
        cartDetailSet.add(cartDetail);
    }

    @Transient
    public Price getPrice(){
        Price price = new Price();
        price.setGrossPrice(new BigDecimal(0));
        price.setNettoPrice(new BigDecimal(0));

        if(cartDetailSet != null && cartDetailSet.size() > 0)
            for(CartDetail cd : cartDetailSet){
                BigDecimal nettoPrice = cd.getPrice().getNettoPrice().multiply(cd.getAmount());
                BigDecimal grossPrice = cd.getPrice().getGrossPrice().multiply(cd.getAmount());
                price.setNettoPrice(price.getNettoPrice().add(nettoPrice));
                price.setGrossPrice(price.getGrossPrice().add(grossPrice));

            }

        return price;
    }

}
