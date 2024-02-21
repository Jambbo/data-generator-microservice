package com.example.datageneratormicro.web.mapper;

import com.example.datageneratormicro.model.test.DataTestOptions;
import com.example.datageneratormicro.web.dto.DataTestOptionsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataTestOptionsMapper
        extends Mappable<DataTestOptions, DataTestOptionsDto> {
}
