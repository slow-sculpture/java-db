package hibernate.shop.order;


import hibernate.shop.complaint.OrderComplaint;
import hibernate.shop.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"orderDetailSet", "orderComplaintSet","orderHistorySet"})
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    BigDecimal totalNetto;
    BigDecimal totalGross;

    @ManyToOne
    @JoinColumn
    User user;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)//EAGER - lazy jest domyslnie
            //jedno zamowienie moze miec wiecej pozycji
            // wlascicielem realacji bedzie order detail
            Set<OrderDetail> orderDetailSet;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<OrderHistory> orderHistorySet;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    public Order(BigDecimal totalGross, User user) {
        this.totalGross = totalGross;
        this.user = user;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetail.setOrder(this);
        orderDetailSet.add(orderDetail);
    }

    public void addOrderHistorySet(OrderHistory orderHistory) {
        orderHistory.setOrder(this);
        this.orderHistorySet.add(orderHistory);
    }
    public OrderHistory getCurrentOrderHistory(){
        return this.getOrderHistorySet().stream()
                .sorted((a, b) -> a.getId().compareTo(b.getId())).findFirst()
                .orElse(new OrderHistory());
    }

    public Set<OrderDetail> getOrderDetailSet() {
        return orderDetailSet;
    }

    public void setOrderDetailSet(Set<OrderDetail> orderDetailSet) {
        this.orderDetailSet = orderDetailSet;
    }
}
