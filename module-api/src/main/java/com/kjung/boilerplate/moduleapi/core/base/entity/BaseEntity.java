package com.kjung.boilerplate.moduleapi.core.base.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntity {
    private String regUserId;
    private String regUserNm;
    private LocalDateTime regDatetime;
    private String updaterId;
    private String updaterNm;
    private LocalDateTime updateDatetime;

    public String getReqUserId() {
        return "test01"; // todo 세션 및 토큰에서 ID 받아오는 로직 구현 필요
    }

}
