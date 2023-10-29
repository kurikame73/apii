package study.querydsl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.ItemReviewsDTO;
import study.querydsl.dto.ReviewDTO;
import study.querydsl.repository.jdbctemplate.JdbcTemplateReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JdbcTemplateReviewService {

    private final JdbcTemplateReviewRepository jdbcTemplateReviewRepository;

    @Transactional
    public void createReview(Long itemId, int rating, String comment) {
        jdbcTemplateReviewRepository.saveReview(itemId, rating, comment);
    }

    public ItemReviewsDTO getAllReviewsByItemId(Long itemId) {
        List<ReviewDTO> reviews = jdbcTemplateReviewRepository.findAllReviewsByItemId(itemId);
        return new ItemReviewsDTO(itemId, reviews);
    }
}
