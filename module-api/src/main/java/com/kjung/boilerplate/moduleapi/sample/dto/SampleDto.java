package com.kjung.boilerplate.moduleapi.sample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SampleDto {
    @Schema(description = "id", example = "1")
    private final Long id;
    @Schema(description = "이름", example = "sample")
    private final String name;

}
