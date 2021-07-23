package com.hart.supermarket.sales.api;

import com.hart.supermarket.sales.repository.Sale;
import com.hart.supermarket.sales.repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sales/")
public class SalesController {

    final Logger logger = LoggerFactory.getLogger(SalesController.class);

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

    @GetMapping(value = "/since/{when}", produces = { "application/json"} )
    public List<Sale> salesSinceTimeframe(@PathVariable Integer when) {
        List<Sale> sales = new ArrayList<Sale>();
        Long unixTime = System.currentTimeMillis() / 1000L;
        Integer check = unixTime.intValue() - when;

        for (Sale s : salesepository.findAll()) {
            if (check < s.getTime()) {
                sales.add(s);
            }
        }
        return sales;
    }


    //PUT
    @PutMapping(value = "/{uuid}", produces = {"application/json"})
    public Sale editASalesContents(@PathVariable String uuid,
                                   @RequestBody Sale sale) {
        Sale theSale = salesepository.findSaleByUuid(uuid);
        theSale.setItemsSku(sale.getItemsSku());
        theSale.setTotal(sale.getTotal());
        salesepository.save(theSale);
        return theSale;
    }


    //DELETE
    @DeleteMapping(value = "/{uuid}", produces = { "application/json"} )
    public String refundSale(@PathVariable String uuid){
        Sale theSale = salesepository.findSaleByUuid(uuid);
        salesepository.delete(theSale);
        //Send a message to the transaction service
        return "Deleted And Refunded Sale # : " + theSale.getUuid();
    }

    //temporary
    @DeleteMapping(value = "/all", produces = { "application/json"} )
    public void deleteAll(){
        salesepository.deleteAll();
    }


}
