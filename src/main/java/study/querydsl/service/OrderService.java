package study.querydsl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.domain.*;
import study.querydsl.repository.ItemRepository;
import study.querydsl.repository.MemberRepository;
import study.querydsl.repository.OrderQueryRepository;
import study.querydsl.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderQueryRepository orderQueryRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findById(memberId).get();
        Item item = itemRepository.findById(itemId).get();

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.cancel();
    }

    public List<Order> findOrdersByMember(Long memberId) {
        return orderRepository.findByMemberIdWithFetchJoin(memberId);
    }

    public Order findOrderDetails(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderQueryRepository.findAllByString(orderSearch);
    }
}
