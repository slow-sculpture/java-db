package hibernate.shop.product;

import hibernate.shop.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    double rating;
    String description;
    LocalDateTime createDate;
    @ManyToOne
    User user;
    @ManyToOne
    Product product;
    boolean isVisible;
}
