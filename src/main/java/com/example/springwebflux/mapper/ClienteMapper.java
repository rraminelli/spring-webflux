package com.example.springwebflux.mapper;

import com.example.springwebflux.dto.ClienteDto;
import com.example.springwebflux.entity.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends BaseMapper<Cliente, ClienteDto>{



}
