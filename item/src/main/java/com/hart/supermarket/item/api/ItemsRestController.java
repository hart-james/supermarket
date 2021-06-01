package com.hart.supermarket.item.api;

import com.google.common.util.concurrent.RateLimiter;
import com.hart.supermarket.item.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items/")
public class ItemsRestController {

//    @Value("${key.name}")
//    String name;  //For Config Server Testing
    @Autowired
    private RateLimiter rateLimiter;

    @GetMapping(value = "/hello", produces = { "application/json" } )
    public String TestService() {
        boolean okToGo = rateLimiter.tryAcquire();
        if (okToGo){
            return ItemsService.GetACliffBarBrand();
        }
        return "Too many requests.";
    }

}
