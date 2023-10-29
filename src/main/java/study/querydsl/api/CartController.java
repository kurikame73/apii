package study.querydsl.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.querydsl.domain.CartItem;
import study.querydsl.service.CartService;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/{memberId}/add")
    public ResponseEntity<CartItem> addItemToCart(@PathVariable Long memberId, @RequestParam Long itemId, @RequestParam int quantity) {
        CartItem cartItem = cartService.addItemToCart(memberId, itemId, quantity);
        return ResponseEntity.ok(cartItem);
    }
}
