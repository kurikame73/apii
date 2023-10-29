package study.querydsl.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderDTO {
    private Long id;
    private String memberName;
    private LocalDateTime orderDate;
    private String status;
    private List<OrderItemDTO> orderItems;

    public OrderDTO() {
    }

    public OrderDTO(study.querydsl.domain.Order order) {
        this.id = order.getId();
        this.memberName = order.getMember().getName();
        this.orderDate = order.getOrderDate();
        this.status = order.getStatus().name();
        this.orderItems = order.getOrderItems().stream()
                .map(OrderItemDTO::new)
                .collect(Collectors.toList());
    }

}
