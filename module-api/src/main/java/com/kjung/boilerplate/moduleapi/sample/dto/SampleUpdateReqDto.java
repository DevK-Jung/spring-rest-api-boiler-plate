package com.kjung.boilerplate.moduleapi.sample.dto;

import com.kjung.boilerplate.moduleapi.core.base.dto.BaseReqDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleUpdateReqDto extends BaseReqDto {
    @NotBlank
    @Schema(description = "이름", example = "sample")
    private String name;
}