package com.example.springwebflux.service;

import com.example.springwebflux.dto.ClienteDto;
import com.example.springwebflux.entity.Cliente;
import com.example.springwebflux.mapper.ClienteMapper;
import com.example.springwebflux.repository.ClienteRepository;
import com.example.springwebflux.repository.pagination.FilterCustomDto;
import com.example.springwebflux.repository.pagination.PageListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClienteService {

    final PaginationService paginationService;
    final ClienteRepository clienteRepository;
    final ClienteMapper clienteMapper;

    public Mono<PageListDto<ClienteDto>> findAll(FilterCustomDto filter) {

        return paginationService.findAll(filter, clienteMapper, Cliente.class);

    }

    public Mono<ClienteDto> findById(String id) {
        return clienteRepository.findById(id)
                .flatMap(cliente -> Mono.just(clienteMapper.entityToDto(cliente)));
    }

    public Mono<ClienteDto> save(ClienteDto clienteDto) {
         return clienteRepository.save(clienteMapper.dtoToEntity(clienteDto))
                 .flatMap(cliente -> Mono.just(clienteMapper.entityToDto(cliente)));
    }

}
