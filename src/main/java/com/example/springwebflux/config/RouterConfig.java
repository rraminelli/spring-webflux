package com.example.springwebflux.config;

import com.example.springwebflux.handler.ClienteHandler;
import com.example.springwebflux.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class RouterConfig {

    @RouterOperations({
            @RouterOperation(method = RequestMethod.GET, path = "/cliente", beanClass = ClienteService.class, beanMethod = "findAll"),
            @RouterOperation(method = RequestMethod.GET, path = "/cliente/{id}", beanClass = ClienteService.class, beanMethod = "findById"),
            @RouterOperation(method = RequestMethod.POST, path = "/cliente", beanClass = ClienteService.class, beanMethod = "save")
    })
    @Bean
    public RouterFunction<ServerResponse> clienteRoutes(ClienteHandler clienteHandler) {
        return route(GET("/cliente"), clienteHandler::findAll)
                .and(route(POST("/cliente"), clienteHandler::save))
                .and(route(GET("/cliente/{id}"), clienteHandler::findById));
    }


}
