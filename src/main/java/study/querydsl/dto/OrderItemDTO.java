package study.querydsl.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {
    private Long itemId;
    private int count;
    private int totalPrice;

    public OrderItemDTO() {
    }

    public OrderItemDTO(study.querydsl.domain.OrderItem orderItem) {
        this.itemId = orderItem.getItemId();
        this.count = orderItem.getCount();
        this.totalPrice = orderItem.getTotalPrice();
    }
}
