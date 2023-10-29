package study.querydsl.web;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AlbumForm {
//DTO
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
    private String artist;
    private String etc;
}
