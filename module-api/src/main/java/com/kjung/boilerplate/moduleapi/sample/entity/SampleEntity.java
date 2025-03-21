package com.kjung.boilerplate.moduleapi.sample.entity;

import com.kjung.boilerplate.moduleapi.core.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleEntity extends BaseEntity {
    private Long id;
    private String name;
}
