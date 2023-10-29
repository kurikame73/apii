package study.querydsl.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.querydsl.dto.ItemReviewsDTO;
import study.querydsl.service.JdbcTemplateReviewService;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final JdbcTemplateReviewService jdbcTemplateReviewService;

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemReviewsDTO> getAllReviewsByItemId(@PathVariable Long itemId) {
        ItemReviewsDTO itemReviews = jdbcTemplateReviewService.getAllReviewsByItemId(itemId);
        return ResponseEntity.ok(itemReviews);
    }

    @PostMapping("/{itemId}")
    public ResponseEntity<Void> createReview(@PathVariable Long itemId, @RequestBody CreateReviewRequest request) {
        jdbcTemplateReviewService.createReview(itemId, request.getRating(), request.getComment());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Getter
    @Setter
    public static class CreateReviewRequest {
        private int rating;
        private String comment;
    }
}
