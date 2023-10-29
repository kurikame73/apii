package study.querydsl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.domain.Item;
import study.querydsl.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional  // 전체 서비스에 트랜잭션 적용
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional(readOnly = true)  // 읽기 전용 트랜잭션 설정
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true)  // 읽기 전용 트랜잭션 설정
    public Optional<Item> findOne(Long itemId) {
        return itemRepository.findById(itemId);
    }

    public void updateItem(Long id, String name, int price, int stockQuantity) {
        Item item = itemRepository.findById(id).get();
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
    }
}
