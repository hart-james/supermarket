package com.hart.supermarket.item.api;

import com.hart.supermarket.item.Item;
import com.hart.supermarket.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items/")
public class ItemsRestController {

//    @Value("${key.name}")
//    String name;  //For Config Server Testing

    @Autowired
    private ItemRepository itemRepository;

    //POST
    @PostMapping(value = "/create", produces = { "application/json" } )
    public String createAnIteam(@RequestBody Item item) {
       itemRepository.save(item);
       return "Saved " + item.getName();
    }


    //GET
    @GetMapping(value= "/{sku}", produces = { "application/json" } )
    public Item getItemBySku(@PathVariable String sku) {
        return itemRepository.findItemBySku(sku);
    }

    @GetMapping(value = "/all", produces = { "application/json" } )
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }


    //PUT
    @PutMapping(value ="/update/{sku}", produces = { "application/json" } )
    public Item editAnItem(@RequestBody Item item){
        itemRepository.save(item);
        return item;
    }

    @PutMapping(value = "/incrementStock/{sku}",produces = { "application/json" } )
    public String incrementItemsStock(@PathVariable String sku) {
        Item item = itemRepository.findItemBySku(sku);
        int stock = item.getStocked() + 1;
        item.setStocked(stock);
        itemRepository.save(item);

        return item.getName() + " incremented";
    }


    @PutMapping(value = "/decrementStock/{sku}",produces = { "application/json" } )
    public String decrementItemsStock(@PathVariable String sku) {
        Item item = itemRepository.findItemBySku(sku);
        int stock = item.getStocked() - 1;
        item.setStocked(stock);
        itemRepository.save(item);

        return item.getName() + " dencremented";
    }


    //DELETE
    @DeleteMapping(value = "/delete", produces = { "application/json"} )
    public String deleteItem(String sku) {
        itemRepository.deleteItemBySku(sku);
        return "Deleted";
    }

    //temporary
    @DeleteMapping(value = "/delete/all", produces = { "application/json"} )
    public String deleteAll(@RequestParam String password) {

        //The password would be retrieved from the config server at startup
        if (password == "pizza") {
            itemRepository.deleteAll();
            return "Deleted Everything";
        }
        return "Did not delete.";

    }


}
