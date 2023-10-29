package study.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.querydsl.domain.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
}
