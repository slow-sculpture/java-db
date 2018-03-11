package hibernate.shop.order;


import hibernate.shop.complaint.OrderComplaint;
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
@EqualsAndHashCode(exclude = {"orderDetailSet", "orderComplaintSet"})
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    BigDecimal totalNetto;
    BigDecimal totalGross;
    String userEmail;


    @OneToMany (mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)//EAGER - lazy jest domyslnie
            //jedno zamowienie moze miec wiecej pozycji
            // wlascicielem realacji bedzie order detail
    Set<OrderDetail> orderDetailSet;

    @OneToOne (mappedBy = "order")
    OrderHistory orderHistory;

    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //join table do laczenia przez tabele dodatkowa
    @JoinTable(
            name = "tabela_order_complaint_history",
            //joinColumns nazwa kolumny w tabeli dodatkowej z kluczem do tabeli laczonej
            // + nazwa pola w encji z kluczem po ktorym laczymy
            joinColumns = @JoinColumn(name = "order_complaint_id", referencedColumnName = "id"),
            //nazwa kolumny z kluczem glownym z encji order
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    Set<OrderComplaint> orderComplaintSet;

    public Order(BigDecimal totalGross, String userEmail) {
        this.totalGross = totalGross;
        this.userEmail = userEmail;
    }

    public void addOrderDetail(OrderDetail orderDetail){
        orderDetail.setOrder(this);
        orderDetailSet.add(orderDetail);
    }
}
