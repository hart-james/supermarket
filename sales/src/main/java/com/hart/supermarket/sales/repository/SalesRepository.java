package com.hart.supermarket.sales.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends MongoRepository<Sale, String> {

    //GET
    Sale findSaleByUuid(String uuid);

}