package com.kjung.boilerplate.moduleapi.sample.mapStruct;


import com.kjung.boilerplate.moduleapi.core.mapStruct.MapStructConfig;
import com.kjung.boilerplate.moduleapi.sample.dto.SampleDto;
import com.kjung.boilerplate.moduleapi.sample.dto.SampleInsertReqDto;
import com.kjung.boilerplate.moduleapi.sample.dto.SampleUpdateReqDto;
import com.kjung.boilerplate.moduleapi.sample.entity.SampleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface SampleMapStruct {
    @Mapping(source = "name", target = "name")
    SampleEntity toEntity(SampleInsertReqDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "dto.name", target = "name")
    SampleEntity toEntityWithId(Long id, SampleUpdateReqDto dto);

    SampleDto toDto(SampleEntity entity);

    List<SampleDto> toDtos(List<SampleEntity> entity);

}
