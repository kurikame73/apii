package study.querydsl.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import study.querydsl.domain.Album;
import study.querydsl.domain.Item;
import study.querydsl.service.ItemService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    //리스트
    @GetMapping(value = "/items")
    public String list(Model model) {

        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping(value = "/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new AlbumForm());
        return "items/createItemForm";
    }

    @PostMapping(value = "/items/new")
    public String create(AlbumForm form) {

        Album album = new Album();
        album.setName(form.getName());
        album.setPrice(form.getPrice());
        album.setStockQuantity(form.getStockQuantity());
        album.setArtist(form.getArtist());
        album.setEtc(form.getEtc());

        itemService.saveItem(album);
        return "redirect:/items";
    }

    @GetMapping(value = "/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Album item = (Album) itemService.findOne(itemId).get();

        AlbumForm form = new AlbumForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setArtist(item.getArtist());
        form.setEtc(item.getEtc());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping(value = "/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form")
    AlbumForm form) {
        itemService.updateItem(itemId, form.getName(), form.getPrice(),
                form.getStockQuantity());
        return "redirect:/items";
    }
}
