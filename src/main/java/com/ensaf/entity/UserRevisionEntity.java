package com.ensaf.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.ensaf.chatroom.utils.ColumnDefinition.USER_DEFAULT_SYSTEM;

@Entity
@Table(name = "revinfo")
@RevisionEntity
@EntityListeners(AuditingEntityListener.class)
@FieldNameConstants
@SuperBuilder(toBuilder = true)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter @Setter
@RequiredArgsConstructor
public class UserRevisionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    @EqualsAndHashCode.Include
    private Long id;
    @RevisionTimestamp
    private long revTimestamp;
    @Column(updatable = false, nullable = false, columnDefinition = USER_DEFAULT_SYSTEM)
    @CreatedBy
    private String editor;

}
