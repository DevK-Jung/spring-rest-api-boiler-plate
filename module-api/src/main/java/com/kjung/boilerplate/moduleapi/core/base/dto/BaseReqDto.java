package com.kjung.boilerplate.moduleapi.core.base.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public abstract class BaseReqDto {
    @Schema(hidden = true)
    public String getReqUserId() {
        return "test01"; // todo 세션 및 토큰에서 ID 받아오는 로직 구현 필요
    }
}
