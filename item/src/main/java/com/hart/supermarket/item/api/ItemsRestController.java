package com.hart.supermarket.item.api;

import com.hart.supermarket.item.service.ItemsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemsRestController {

    @GetMapping(value = "/hello", produces = { "application/json" } )
    public String TestService() {
        return ItemsService.GetACliffBarBrand();
    }

}
