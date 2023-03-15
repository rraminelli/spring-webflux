package com.example.springwebflux.service;

import com.example.springwebflux.mapper.BaseMapper;
import com.example.springwebflux.repository.pagination.FilterCustomDto;
import com.example.springwebflux.repository.pagination.PageFluxDto;
import com.example.springwebflux.repository.pagination.PageListDto;
import com.example.springwebflux.repository.pagination.PaginationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaginationService {

    final PaginationRepository paginationRepository;

    public <T, DTO> Mono<PageListDto<DTO>> findAll(FilterCustomDto filter, BaseMapper<T, DTO> mapper, Class<T> clazzEntity) {

        PageFluxDto<T> pagination = paginationRepository.pagination(filter, clazzEntity);

        Mono<Long> countMono = pagination.getCount();

        Mono<List<DTO>> contentMono = pagination.getContent().collectList()
                .map(clientes -> clientes.stream().map(mapper::entityToDto).toList());

        Mono<PageListDto<DTO>> pageListMono = countMono
                .zipWith(contentMono, (count, content) ->
                        new PageListDto<>(
                                content,
                                count == 0 ? 1 : (int)Math.ceil((double)count / (double)filter.getSize()),
                                count.intValue(),
                                filter.getPage(),
                                filter.getSize())
                );

        return pageListMono;
    }


}
