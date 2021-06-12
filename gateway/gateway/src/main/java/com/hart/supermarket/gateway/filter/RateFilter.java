package com.hart.supermarket.gateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RateFilter extends AbstractGatewayFilterFactory {

    final Logger logger = LoggerFactory.getLogger(RateFilter.class);

    // BUILD A SEPARATE ACCESS CONTROL FILTER

    @Autowired
    private RateLimiter rateLimiter;

    @Override
    public GatewayFilter apply(Object config) {
        logger.info("RATE LIMITING FILTER");

        return (exchange, chain) -> {

            boolean okToGo = rateLimiter.tryAcquire();
            if (okToGo){
                logger.info("RATE LIMITING FILTER");
                return chain.filter(exchange);
            }

            // set TOO_MANY_REQUESTS 429 response and stop the processing
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete();
        };
    }



}
