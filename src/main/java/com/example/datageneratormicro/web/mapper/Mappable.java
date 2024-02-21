package com.example.datageneratormicro.web.mapper;

//E - Entity, D - Dto
public interface Mappable<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

}
