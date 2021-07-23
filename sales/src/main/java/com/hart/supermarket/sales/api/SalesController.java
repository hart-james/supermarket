package com.hart.supermarket.sales.api;

import com.hart.supermarket.sales.repository.Sale;
import com.hart.supermarket.sales.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales/")
public class SalesController {

    @Autowired
    private SaleRepository salesepository;

    //POST
    @PostMapping(value = "/create", produces = { "application/json" } )
    public String createAnIteam(@RequestBody Sale sale) {
        sale.setUuid();
        salesepository.save(sale);
        //send a message to the transaction server
        return "Saved " + sale.getUuid();
    }

    //GET
    @GetMapping(value = "/all", produces = { "application/json"} )
    public List<Sale> getAllSales(){
        return salesepository.findAll();
    }

    @GetMapping(value = "/{uuid}", produces = { "application/json"} )
    public Sale findSaleByUuid(@PathVariable String uuid){
        return salesepository.findSaleByUuid(uuid);
    }

    //DELETE
    @DeleteMapping(value = "/all", produces = { "application/json"} )
    public String refundSale(@PathVariable String uuid){
        Sale theSale = salesepository.findSaleByUuid(uuid);
        salesepository.delete(theSale);
        //Send a message to the transaction service
        return "Deleted Sale # : " + theSale.getUuid();
    }

    //temporary
    @DeleteMapping(value = "/all", produces = { "application/json"} )
    public void deleteAll(){
        salesepository.deleteAll();
    }


}
