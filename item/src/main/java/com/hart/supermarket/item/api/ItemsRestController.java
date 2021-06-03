package com.hart.supermarket.item.api;

import com.google.common.util.concurrent.RateLimiter;
import com.hart.supermarket.item.Item;
import com.hart.supermarket.item.repository.ItemRepository;
import com.hart.supermarket.item.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items/")
public class ItemsRestController {

//    @Value("${key.name}")
//    String name;  //For Config Server Testing
    @Autowired
    private RateLimiter rateLimiter;

    @Autowired
    private ItemRepository repository;

    @GetMapping(value = "/hello", produces = { "application/json" } )
    public String TestService() {
        boolean okToGo = rateLimiter.tryAcquire();
        if (okToGo){
            return ItemsService.GetACliffBarBrand();
        }
        return "Too many requests.";
    }

    @PostMapping(value = "/create", produces = { "application/json" } )
    public String createAnIteam() {
       Item createdItem = ItemsService.createAnItem(); //dummy object in service
       repository.save(createdItem);
       return "Saved";
    }
    
    @GetMapping(value= "/{sku}", produces = { "application/json" } )
    public Item getItemBySku(@PathVariable String sku) {
        return repository.findItemBySku(sku);
    }

}
