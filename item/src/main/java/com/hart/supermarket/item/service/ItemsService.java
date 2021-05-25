package com.hart.supermarket.item.service;

import com.hart.supermarket.item.Item;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class ItemsService {

    public static String GetACliffBarBrand(){

        //helpers
        UUID uuid = UUID.randomUUID();
        Date date = new Date(System.currentTimeMillis());

        //Entity
        Item item = new Item("Chocolate Chip Energy Bar",
                "Cliff",
                "Health",
                15.00,
                 uuid,
                date,
                false);

        return item.getBrand();
    }


}
