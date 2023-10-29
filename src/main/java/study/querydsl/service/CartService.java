package study.querydsl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.domain.Cart;
import study.querydsl.domain.CartItem;
import study.querydsl.domain.Item;
import study.querydsl.domain.Member;
import study.querydsl.repository.CartItemRepository;
import study.querydsl.repository.CartRepository;
import study.querydsl.repository.ItemRepository;
import study.querydsl.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    public CartItem addItemToCart(Long memberId, Long itemId, int quantity) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("회원 못찾음"));
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("상품 못찾음"));

        Cart cart = member.getCart();
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setQuantity(quantity);

        return cartItemRepository.save(cartItem);
    }
}
