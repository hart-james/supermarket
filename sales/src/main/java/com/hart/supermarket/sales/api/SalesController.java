package com.hart.supermarket.sales.api;

import com.hart.supermarket.sales.repository.Sale;
import com.hart.supermarket.sales.repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sales/")
public class SalesController {

    final Logger logger = LoggerFactory.getLogger(SalesController.class);

    @Autowired
    private RedisTemplate template;
    @Autowired
    private ChannelTopic postTopic;
    @Autowired
    private ChannelTopic deleteTopic;
    @Autowired
    private SaleRepository salesRepository;

    //POST
    @PostMapping(value = "/create", produces = { "application/json" } )
    public String createAnIteam(@RequestBody Sale sale) {
        //sale.setUuid();
        //salesepository.save(sale);
        template.convertAndSend(postTopic.getTopic(), sale.getEmployee());
        return "Saved " + sale.getUuid();
    }

    //GET
    @GetMapping(value = "/all", produces = { "application/json"} )
    public List<Sale> getAllSales(){
        return salesRepository.findAll();
    }

    @GetMapping(value = "/{uuid}", produces = { "application/json"} )
    public Sale findSaleByUuid(@PathVariable String uuid){
        return salesRepository.findSaleByUuid(uuid);
    }

    @GetMapping(value = "/since/{when}", produces = { "application/json"} )
    public List<Sale> salesSinceTimeframe(@PathVariable Integer when) {
        List<Sale> sales = new ArrayList<Sale>();
        Long unixTime = System.currentTimeMillis() / 1000L;
        Integer check = unixTime.intValue() - when;

        for (Sale s : salesRepository.findAll()) {
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
        Sale theSale = salesRepository.findSaleByUuid(uuid);
        theSale.setItemsSku(sale.getItemsSku());
        theSale.setTotal(sale.getTotal());
        salesRepository.save(theSale);
        return theSale;
    }


    //DELETE
    @DeleteMapping(value = "/{uuid}", produces = { "application/json"} )
    public String refundSale(@PathVariable String uuid){
        Sale theSale = salesRepository.findSaleByUuid(uuid);
        salesRepository.delete(theSale);
        template.convertAndSend(postTopic.getTopic(), uuid);
        return "Deleted And Refunded Sale # : " + theSale.getUuid();
    }

    //temporary
    @DeleteMapping(value = "/all", produces = { "application/json"} )
    public void deleteAll(){
        salesRepository.deleteAll();
    }


}
