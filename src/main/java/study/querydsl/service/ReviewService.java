package study.querydsl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.domain.Item;
import study.querydsl.domain.Member;
import study.querydsl.domain.Order;
import study.querydsl.domain.Review;
import study.querydsl.repository.ItemRepository;
import study.querydsl.repository.MemberRepository;
import study.querydsl.repository.OrderRepository;
import study.querydsl.repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long addReview(Long memberId, Long itemid, Review review) {
        Member member = memberRepository.findById(memberId).get();
        Item item = itemRepository.findById(itemid).get();

        if (!hasOrderedItem(member, item)) {
            throw new IllegalArgumentException("회원이 해당 상품을 주문하지 않았다.");
        }

        //연관관계
        review.setItem(item);
        reviewRepository.save(review);
        return review.getId();
    }

    private boolean hasOrderedItem(Member member, Item item) {
        List<Order> orders = orderRepository.findByMember(member);
        for (Order order : orders) {
            if (order.containsItem(item)) {
                return true;
            }
        }
        return false;
    }
}
