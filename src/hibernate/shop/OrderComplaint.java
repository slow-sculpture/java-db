package hibernate.shop;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class OrderComplaint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String message;

    @Enumerated (value = EnumType.STRING)
    ComplaintStatus complaintStatus;

    @ManyToMany
    Set<Order> orderSet;
}
