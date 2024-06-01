package com.thecoders.cartunnbackend.shared.domain.model.entities;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditableModel {
    @CreatedDate
    @Column(nullable = false,updatable = false)
    private Date createdAt;

    @CreatedDate
    @Column(nullable = false)
    private Date updatedAt;
}
