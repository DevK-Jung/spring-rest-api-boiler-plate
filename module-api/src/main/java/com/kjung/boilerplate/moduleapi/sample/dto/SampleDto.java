package com.kjung.boilerplate.moduleapi.sample.dto;

import com.kjung.boilerplate.moduleapi.core.base.dto.BaseRespDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SampleDto extends BaseRespDto {
    @Schema(description = "id", example = "1")
    private Long id;
    @Schema(description = "이름", example = "sample")
    private String name;

}
