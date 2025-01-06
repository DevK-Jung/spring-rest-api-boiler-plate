package com.kjung.boilerplate.moduleapi.sample.service.impl;

import com.kjung.boilerplate.moduleapi.sample.dto.SampleDto;
import com.kjung.boilerplate.moduleapi.sample.dto.SampleInsertReqDto;
import com.kjung.boilerplate.moduleapi.sample.dto.SampleUpdateReqDto;
import com.kjung.boilerplate.moduleapi.sample.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SampleServiceImpl implements SampleService {

    @Override
    public SampleDto getSample(Long id) {
        log.info("조회");

        return new SampleDto(id, "sample");
    }

    @Override
    public SampleDto insertSample(SampleInsertReqDto param) {
        log.info("등록");

        return new SampleDto(1L, param.getName());
    }

    @Override
    public SampleDto updateSample(Long id, SampleUpdateReqDto param) {
        log.info("수정");

        return new SampleDto(id, param.getName());
    }

    @Override
    public void deleteSample(Long id) {
        log.info("삭제");
    }
}
