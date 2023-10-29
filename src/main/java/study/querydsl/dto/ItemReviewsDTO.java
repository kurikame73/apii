package study.querydsl.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemReviewsDTO {
    private Long itemId;
    private List<ReviewDTO> reviews;

    public ItemReviewsDTO(Long itemId, List<ReviewDTO> reviews) {
        this.itemId = itemId;
        this.reviews = reviews;
    }
}
