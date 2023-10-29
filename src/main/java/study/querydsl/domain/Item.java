package study.querydsl.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import study.querydsl.exception.NotEnoughStockException;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
    private String itemDetail;

    @OneToMany(mappedBy = "item")
    @JsonBackReference
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();

    //비즈니스 로직
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");

        }
        this.stockQuantity = restStock;
    }

    public String getDtype() {
        throw new UnsupportedOperationException("This method should be overridden in subclasses");
    }
}
