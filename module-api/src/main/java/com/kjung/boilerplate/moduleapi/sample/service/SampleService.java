package com.kjung.boilerplate.moduleapi.sample.service;

import com.kjung.boilerplate.moduleapi.sample.dto.SampleDto;
import com.kjung.boilerplate.moduleapi.sample.dto.SampleInsertReqDto;
import com.kjung.boilerplate.moduleapi.sample.dto.SampleUpdateReqDto;

import java.util.List;

public interface SampleService {

    SampleDto getSample(Long id);
    List<SampleDto> getSamples();

    SampleDto insertSample(SampleInsertReqDto param);

    SampleDto updateSample(Long id, SampleUpdateReqDto param);

    void deleteSample(Long id);
}
