package com.kjung.boilerplate.moduleapi.core.base.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseRespDto {

    @Schema(description = "등록자 ID")
    private String regUserId;
    @Schema(description = "등록일시")
    private LocalDateTime regDatetime;
    @Schema(description = "등록자 명")
    private String regUserNm;
    @Schema(description = "수정자 ID")
    private String updaterId;
    @Schema(description = "수정자 명")
    private String updaterNm;
    @Schema(description = "수정일시")
    private LocalDateTime updateDatetime;
}
