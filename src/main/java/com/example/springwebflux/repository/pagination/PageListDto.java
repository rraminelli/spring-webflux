package com.example.springwebflux.repository.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageListDto<T> {

    private List<T> content;
    private int totalPages;
    private int totalItems;
    private int page;
    private int size;

}
