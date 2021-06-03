package com.hart.supermarket.item.repository;

import com.hart.supermarket.item.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
    public Item findItemBySku(String sku);
}

