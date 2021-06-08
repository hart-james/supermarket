package com.hart.supermarket.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class RateFilter extends AbstractGatewayFilterFactory {

    final Logger logger = LoggerFactory.getLogger(RateFilter.class);

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            logger.info("RATE LIMITING FILTER");
            return chain.filter(exchange);
        };
    }


}
