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
@EqualsAndHashCode(exclude = {"cartDetailSet", "user"})
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

    public BigDecimal getTotalGrossPrice(){
      /*  double sum = cartDetailSet.stream()
                .mapToDouble(cd -> cd.getAmount().multiply(cd.getPrice().getGrossPrice()).doubleValue()).sum();
        return new BigDecimal(sum);*/
/*
        BigDecimal totalGross = new BigDecimal(0);
        cartDetailSet.forEach(cd->totalGross.add(cd.getAmount().multiply(cd.getPrice().getGrossPrice())));
        return totalGross;*/

        BigDecimal totalGross =
                cartDetailSet.stream().map(cd->cd.getAmount().multiply(cd.getPrice().getGrossPrice()))
                        .reduce(BigDecimal.ZERO,BigDecimal::add);

        return totalGross;
    }
    public BigDecimal getTotalNettoPrice(){
        BigDecimal totalNetto =
        cartDetailSet.stream().map(cd->cd.getAmount().multiply(cd.getPrice().getNettoPrice()))
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        return totalNetto;
    }



}
