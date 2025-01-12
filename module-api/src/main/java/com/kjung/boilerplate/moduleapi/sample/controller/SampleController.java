package com.kjung.boilerplate.moduleapi.sample.controller;

import com.kjung.boilerplate.moduleapi.sample.dto.SampleDto;
import com.kjung.boilerplate.moduleapi.sample.dto.SampleInsertReqDto;
import com.kjung.boilerplate.moduleapi.sample.dto.SampleUpdateReqDto;
import com.kjung.boilerplate.moduleapi.sample.service.SampleService;
import com.kjung.boilerplate.modulecommon.core.base.AbstractController;
import com.kjung.boilerplate.modulecommon.core.response.ResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Sample Controller", description = "Sample Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/samples")
public class SampleController extends AbstractController {

    private final SampleService sampleService;


    @Operation(summary = "Read", description = "Read")
    @GetMapping("/{id}")
    public ResponseWrapper<SampleDto> getSample(@Parameter(name = "id", description = "id", example = "1")
                                                @PathVariable(name = "id") Long id) {
        return makeOkResponse(sampleService.getSample(id));
    }

    @Operation(summary = "Create", description = "Create")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseWrapper<SampleDto> insertSample(@Validated @RequestBody SampleInsertReqDto param) {
        return makeCreatedResponse(sampleService.insertSample(param));
    }

    @Operation(summary = "Update", description = "Update")
    @PutMapping("/{id}")
    public ResponseWrapper<SampleDto> updateSample(@Parameter(name = "id", description = "id", example = "1")
                                                   @PathVariable(name = "id") Long id,
                                                   @Validated @RequestBody SampleUpdateReqDto param) {
        return makeOkResponse(sampleService.updateSample(id, param));
    }

    @Operation(summary = "Delete", description = "Delete")
    @DeleteMapping("/{id}")
    public void deleteSample(@Parameter(name = "id", description = "id", example = "1")
                             @PathVariable(name = "id") Long id) {
        sampleService.deleteSample(id);
    }

    @Operation(summary = "file upload", description = "file upload")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public boolean upload(@Parameter(description = "file") final MultipartFile file) {
        return true;
    }
}
