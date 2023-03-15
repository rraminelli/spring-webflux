package com.example.springwebflux.handler;

import com.example.springwebflux.dto.ClienteDto;
import com.example.springwebflux.repository.pagination.FilterCustomDto;
import com.example.springwebflux.service.ClienteService;
import com.example.springwebflux.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClienteHandler {

    final ClienteService clienteService;
    final ObjectValidator validator;

    public Mono<ServerResponse> findAll(ServerRequest request) {

        FilterCustomDto filterCustom = new FilterCustomDto();
        filterCustom.setValues(request.queryParams().toSingleValueMap());
        filterCustom.setPage(Integer.parseInt(request.queryParam("page").orElse("0")));
        filterCustom.setSize(Integer.parseInt(request.queryParam("size").orElse("20")));

        filterCustom.getValues().remove("page");
        filterCustom.getValues().remove("size");
        filterCustom.getValues().remove("trace");

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(clienteService.findAll(filterCustom), ClienteDto.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(clienteService.findById(request.pathVariable("id")), ClienteDto.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(ClienteDto.class)
                .doOnNext(validator::validate)
                .flatMap(clienteService::save)
                .flatMap(cliente -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(cliente)));
    }


}
