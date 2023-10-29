package study.querydsl.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.querydsl.dto.OrderDTO;
import study.querydsl.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderControllerApi {

    private final OrderService orderService;

    @GetMapping("/{memberId}")
    public List<OrderDTO> getOrdersByMember(@PathVariable Long memberId) {
        List<study.querydsl.domain.Order> orders = orderService.findOrdersByMember(memberId);
        return orders.stream().map(OrderDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{memberId}/{orderId}")
    public ResponseEntity<OrderDTO> getOrderDetail(@PathVariable Long memberId, @PathVariable Long orderId) {
        study.querydsl.domain.Order order = orderService.findOrderDetails(orderId);
        if (order != null && order.getMember().getId().equals(memberId)) {
            return ResponseEntity.ok(new OrderDTO(order));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/order")
    public ResponseEntity<?> order(@RequestBody OrderRequest request) {
        orderService.order(request.getMemberId(), request.getItemId(), request.getCount());
        return ResponseEntity.ok("Order completed");
    }

    @Getter
    @Setter
    static class OrderRequest {
        private Long memberId;
        private Long itemId;
        private int count;
    }
}
