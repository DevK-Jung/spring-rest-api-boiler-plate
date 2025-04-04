package com.kjung.boilerplate.httpclient.httpInterface.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SampleDto {
    private Long id;
    private String userId;

    public SampleDto(String userId) {
        this.userId = userId;
    }
}

