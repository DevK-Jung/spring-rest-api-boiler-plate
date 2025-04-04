package com.kjung.boilerplate.moduleapi.core.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @CreatedBy
    @Column(name = "REG_USER_ID", updatable = false, length = 50)
    private String regUserId;

    @CreatedDate
    @Column(name = "REG_DATETIME", updatable = false)
    private LocalDateTime regDatetime;

    @LastModifiedBy
    @Column(name = "UPDATER_ID", length = 50)
    private String updaterId;

    @LastModifiedDate
    @Column(name = "UPDATE_DATETIME")
    private LocalDateTime updateDatetime;

}
