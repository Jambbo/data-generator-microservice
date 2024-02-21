package com.example.datageneratormicro.web.mapper;

import com.example.datageneratormicro.model.Data;
import com.example.datageneratormicro.web.dto.DataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // Чтобы сгенерировался маппер и мы могли его использовать
public interface DataMapper extends Mappable<Data, DataDto> {


}
