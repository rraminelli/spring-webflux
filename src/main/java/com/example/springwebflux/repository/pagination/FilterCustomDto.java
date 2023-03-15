package com.example.springwebflux.repository.pagination;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class FilterCustomDto {

    private Map<String, String> values;
    private String[] sort;
    private int page;
    private int size;

}
