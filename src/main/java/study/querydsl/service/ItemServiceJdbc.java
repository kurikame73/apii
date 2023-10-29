package study.querydsl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.domain.Item;
import study.querydsl.repository.jdbctemplate.JdbcTemplateItemRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceJdbc {

    private final JdbcTemplateItemRepository jdbcTemplateItemRepository;

    public String findItemDetail(Long itemId) {
        Item item = jdbcTemplateItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
        return item.getItemDetail();
    }


    @Transactional
    public void addItemDetail(Long itemId, String itemDetail) {
        jdbcTemplateItemRepository.addItemDetail(itemId, itemDetail);
        log.info("addItemDetail called with itemId: {}", itemId);
    }

    @Transactional
    public void deleteItemDetail(Long itemId) {
        jdbcTemplateItemRepository.deleteItemDetail(itemId);
    }

    @Transactional
    public void updateItemDetail(Long itemId, String itemDetail) {
        Item item = jdbcTemplateItemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Item not found"));
        item.setItemDetail(itemDetail);
    }
}
