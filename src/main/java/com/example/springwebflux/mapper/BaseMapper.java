package com.example.springwebflux.mapper;

import java.util.List;

public interface BaseMapper<T, DTO> {

    List<DTO> listEntityToDto(List<T> list);

    DTO entityToDto(T entity);

    T dtoToEntity(DTO dto);

}
