package study.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.querydsl.domain.Item;
import study.querydsl.domain.Member;
import study.querydsl.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByMember(Member member);

    Order save(Order order);

    Optional<Order> findById(Long id);

    @Query("SELECT o FROM Order o WHERE o.member.id = :memberId")
    List<Order> findByMemberId(@Param("memberId") Long memberId);

    @Query("SELECT o FROM Order o JOIN FETCH o.orderItems oi JOIN FETCH oi.item WHERE o.member.id = :memberId")
    List<Order> findByMemberIdWithFetchJoin(@Param("memberId") Long memberId);

}
