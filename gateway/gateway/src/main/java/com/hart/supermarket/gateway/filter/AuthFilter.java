package com.hart.supermarket.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory {

    final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            logger.info("AUTHENTICATION FILTER");
            return chain.filter(exchange);
        };
    }


}
