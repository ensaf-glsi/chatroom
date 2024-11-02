package com.ensaf.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.ensaf.chatroom.utils.ColumnDefinition.TIMESTAMP_DEFAULT_NOW;
import static com.ensaf.chatroom.utils.ColumnDefinition.USER_DEFAULT_SYSTEM;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@FieldNameConstants
@SuperBuilder(toBuilder = true)
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public abstract class AbstractAuditable<I extends Serializable> extends AbstractPersistable<I> {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    @NotAudited
    @Column(updatable = false, nullable = false, columnDefinition = TIMESTAMP_DEFAULT_NOW)
    @CreatedDate
    private LocalDateTime createdDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    @NotAudited
    @Column(updatable = false, nullable = false, columnDefinition = USER_DEFAULT_SYSTEM)
    @CreatedBy
    private String createdBy;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    @NotAudited
    @Column(nullable = false, columnDefinition = TIMESTAMP_DEFAULT_NOW)
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    @NotAudited
    @Column(nullable = false, columnDefinition = USER_DEFAULT_SYSTEM)
    @LastModifiedBy
    private String lastModifiedBy;

}
