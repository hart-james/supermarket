package com.hart.supermarket.sales.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends MongoRepository<Sale, String> {

    //GET
    Sale findSaleByUuid(String uuid);

    //DELETE
    String deleteSaleByUuid(String uuid);


}