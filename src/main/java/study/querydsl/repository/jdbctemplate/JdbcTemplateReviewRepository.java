package study.querydsl.repository.jdbctemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import study.querydsl.domain.Review;
import study.querydsl.dto.ReviewDTO;
import study.querydsl.repository.ItemRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcTemplateReviewRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ItemRepository itemRepository;

    public void saveReview(Long itemId, int rating, String comment) {
        String sql = "INSERT INTO review (item_id, rating, comment) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, itemId, rating, comment);
    }

    public List<ReviewDTO> findAllReviewsByItemId(Long itemId) {
        String sql = "SELECT * FROM review WHERE item_id = ?";
        return jdbcTemplate.query(sql, new Object[]{itemId}, (rs, rowNum) -> {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setId(rs.getLong("review_id"));
            reviewDTO.setRating(rs.getInt("rating"));
            reviewDTO.setComment(rs.getString("comment"));
            return reviewDTO;
        });
    }


}
