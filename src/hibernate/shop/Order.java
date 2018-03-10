package hibernate.shop;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table (name="orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    BigDecimal totalNetto;
    BigDecimal totalGross;
    String userEmail;

    public Order(BigDecimal totalGross, String userEmail) {
        this.totalGross = totalGross;
        this.userEmail = userEmail;
    }
}
