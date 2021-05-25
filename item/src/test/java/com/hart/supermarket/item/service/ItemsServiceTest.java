package com.hart.supermarket.item.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemsServiceTest {

    @Test
    void UnitTestForHelloService(){
        String response = ItemsService.GetACliffBarBrand();
        assertEquals("Cliff", ItemsService.GetACliffBarBrand());
    }

}