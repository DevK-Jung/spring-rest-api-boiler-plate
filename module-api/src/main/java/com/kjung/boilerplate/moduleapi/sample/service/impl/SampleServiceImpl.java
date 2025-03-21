package com.kjung.boilerplate.moduleapi.sample.service.impl;

import com.kjung.boilerplate.moduleapi.sample.dto.SampleDto;
import com.kjung.boilerplate.moduleapi.sample.dto.SampleInsertReqDto;
import com.kjung.boilerplate.moduleapi.sample.dto.SampleUpdateReqDto;
import com.kjung.boilerplate.moduleapi.sample.entity.SampleEntity;
import com.kjung.boilerplate.moduleapi.sample.mapStruct.SampleMapStruct;
import com.kjung.boilerplate.moduleapi.sample.repository.SampleRepository;
import com.kjung.boilerplate.moduleapi.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {

    private final SampleMapStruct sampleMapStruct;

    private final SampleRepository sampleRepository;

    @Override
    public SampleDto getSample(Long id) {
        log.info("조회");

        SampleEntity entity = sampleRepository.findById(id)
                .orElseThrow();

        return sampleMapStruct.toDto(entity);
    }

    @Override
    public List<SampleDto> getSamples() {
        log.info("목록");

        List<SampleEntity> all = sampleRepository.findAll();

        return sampleMapStruct.toDtos(all);
    }

    @Override
    public SampleDto insertSample(SampleInsertReqDto param) {
        log.info("등록");

        SampleEntity entity = sampleMapStruct.toEntity(param);

        SampleEntity saved = sampleRepository.save(entity);

        return sampleMapStruct.toDto(saved);
    }

    @Override
    public SampleDto updateSample(Long id, SampleUpdateReqDto param) {
        log.info("수정");

        SampleEntity entity = sampleMapStruct.toEntityWithId(id, param);

        SampleEntity saved = sampleRepository.save(entity);

        return sampleMapStruct.toDto(saved);
    }

    @Override
    public void deleteSample(Long id) {
        log.info("삭제");

        sampleRepository.deleteById(id);
    }
}
