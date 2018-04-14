package hibernate.shop.product;

import hibernate.shop.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ProductRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int rating;
    String description;
    LocalDateTime createDate;
    @ManyToOne
    User user;
    @ManyToOne
    Product product;
    boolean isVisible;
}
