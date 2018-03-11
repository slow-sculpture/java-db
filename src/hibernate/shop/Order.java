package hibernate.shop;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table (name="orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "orderDetailSet")
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    BigDecimal totalNetto;
    BigDecimal totalGross;
    String userEmail;


    @OneToMany (mappedBy = "order", cascade = CascadeType.ALL)
            //jedno zamowienie moze miec wiecej pozycji
            // wlascicielem realacji bedzie order detail
    Set<OrderDetail> orderDetailSet;

    public Order(BigDecimal totalGross, String userEmail) {
        this.totalGross = totalGross;
        this.userEmail = userEmail;
    }

    public void addOrderDetail(OrderDetail orderDetail){
        orderDetail.setOrder(this);
        orderDetailSet.add(orderDetail);
    }
}
