package com.example.shoppingmall.repository;

import com.example.shoppingmall.constant.ItemSellStatus;
import com.example.shoppingmall.entity.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @BeforeEach
    public void setUp() {
        for(int i = 0; i < 10; i++) {
            Item item = new Item();
            item.setItemNm("test");
            item.setPrice(1000*i);
            item.setItemDetail("testDetail"+i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegiDt(LocalDateTime.now());
            item.setUpdaDt(LocalDateTime.now());
            itemRepository.save(item);
        }
    }

    @AfterEach
    public void destroy() {
        itemRepository.deleteAll();
    }

    @Test
    @DisplayName("findByItemNm 테스트")
    public void findByItemNmTest() {
        List<Item> list = itemRepository.findByItemNm("test");
        assertEquals(list.size(), 10);
    }

    @Test
    @DisplayName("findByItemNmOrItemDetailTest 테스트")
    public void findByItemNmOrItemDetailTest() {
        List<Item> itemList = itemRepository.findAll();
        for(int i = 0; i < 10; i++) {
            assertEquals(itemList.get(i),
                    itemRepository.findByItemNmOrItemDetail(null, itemList.get(i).getItemDetail()).get(0));
        }
    }

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

    @Test
    @DisplayName("findByPriceLessThanTest 테스트")
    public void findByPriceLessThanTest() {
        List<Item> list = itemRepository.findByPriceLessThan(5000);
        for(Item item : list) {
            assertTrue(item.getPrice() < 5000 );
        }
    }
}