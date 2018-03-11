package hibernate.shop;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderComplaint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String message;

    @Enumerated (value = EnumType.STRING)
    ComplaintStatus complaintStatus;

    @ManyToMany (mappedBy = "orderComplaintSet")
    Set<Order> orderSet;
}
