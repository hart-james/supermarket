package com.hart.supermarket.item.api;

import com.hart.supermarket.item.service.ItemsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items/")
public class ItemsRestController {

//    @Value("${key.name}")
//    String name;  //For Config Server Testing

    @GetMapping(value = "/hello", produces = { "application/json" } )
    public String TestService() {
        return ItemsService.GetACliffBarBrand();
    }

}
