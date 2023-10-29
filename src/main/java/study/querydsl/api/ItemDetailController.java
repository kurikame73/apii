package study.querydsl.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.querydsl.service.ItemServiceJdbc;

@RestController
@RequestMapping("/api/item-detail")
@RequiredArgsConstructor
public class ItemDetailController {

    private final ItemServiceJdbc itemService;

    @GetMapping("/{itemId}")
    public ResponseEntity<String> getItemDetail(@PathVariable Long itemId) {
        String itemDetail = itemService.findItemDetail(itemId);
        return ResponseEntity.ok(itemDetail);
    }

    @PostMapping("/{itemId}")
    public ResponseEntity<String> addItemDetail(@PathVariable Long itemId, @RequestBody String itemDetail) {
        itemService.addItemDetail(itemId, itemDetail);
        return ResponseEntity.ok("Item detail added successfully!");
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> deleteItemDetail(@PathVariable Long itemId) {
        itemService.deleteItemDetail(itemId);
        return ResponseEntity.ok("Item detail deleted successfully!");
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<String> updateItemDetail(@PathVariable Long itemId, @RequestBody String itemDetail) {
        itemService.updateItemDetail(itemId, itemDetail);
        return ResponseEntity.ok("Item detail updated successfully!");
    }
}
