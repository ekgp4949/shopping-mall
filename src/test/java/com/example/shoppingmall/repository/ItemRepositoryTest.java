package com.example.shoppingmall.repository;

import com.example.shoppingmall.constant.ItemSellStatus;
import com.example.shoppingmall.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        Item item = new Item();
        item.setItemNm("test");
        item.setPrice(1000);
        item.setItemDetail("testDetail");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegiDt(LocalDateTime.now());
        item.setUpdaDt(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);

        Item findedItem = itemRepository.findById(savedItem.getId()).get();
        assertEquals(findedItem.getId(), savedItem.getId());
        assertEquals(findedItem.getItemNm(), item.getItemNm());
        assertEquals(findedItem.getPrice(), item.getPrice());
        assertEquals(findedItem.getItemSellStatus(), item.getItemSellStatus());
        assertEquals(findedItem.getItemDetail(), item.getItemDetail());
    }
}