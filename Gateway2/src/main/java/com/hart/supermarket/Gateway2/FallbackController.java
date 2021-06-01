package com.hart.supermarket.Gateway2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/itemsFallback")
    public Mono<String> itemsServiceFallback(){
        return Mono.just("Items Service is taking too long to respond or is down");
    }
}
