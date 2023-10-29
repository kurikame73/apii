package study.querydsl.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import study.querydsl.domain.*;

import java.util.List;

import static study.querydsl.domain.QMember.member;
import static study.querydsl.domain.QOrder.order;

@Repository
public class OrderQueryRepository {

    @PersistenceContext
    EntityManager em;

    public List<Order> findAllByString(OrderSearch orderSearch) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QOrder qOrder = order;
        QMember qMember = member;

        JPAQuery<Order> query = queryFactory
                .selectFrom(qOrder)
                .join(qOrder.member, qMember)
                .where(
                        statusEq(orderSearch.getOrderStatus()),
                        nameLike(orderSearch.getMemberName())
                )
                .limit(1000);

        return query.fetch();
    }

    public BooleanExpression statusEq(OrderStatus orderStatus) {
        if (orderStatus == null) {
            return null;
        }

        return order.status.eq(orderStatus);
    }

    public BooleanExpression nameLike(String memberName) {
        if (memberName == null) {
            return null;
        }

        return member.name.like(memberName);
    }
}
