package com.hart.supermarket.item.repository;

import com.hart.supermarket.item.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

    //GET
    Item findItemBySku(String sku);

    //DELETE
    String deleteItemBySku(String sku);


}

