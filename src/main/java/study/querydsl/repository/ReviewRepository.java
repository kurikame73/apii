package study.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {


}
