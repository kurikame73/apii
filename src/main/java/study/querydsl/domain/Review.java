package study.querydsl.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonManagedReference
    private Item item;  // OrderItem 대신 Item 엔터티와의 관계

    private int rating;
    private String comment;

    public void setReviewDetails(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }
}
