package com.ensaf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Persistable;
import org.springframework.data.util.ProxyUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * Base class for entities that provides default implementations for
 * ID handling and persistence state tracking.
 *
 * @param <I> the type of the identifier
 */
@MappedSuperclass
@FieldNameConstants
@SuperBuilder(toBuilder = true)
@RequiredArgsConstructor
@ToString
public abstract class AbstractPersistable<I extends Serializable> implements Persistable<I> {

    // a voir avec swagger
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Override
    public abstract I getId();

    /**
     * Indicates whether the entity is new (not yet persisted).
     */
    // a voir avec swagger
    @JsonIgnore
    @Transient
    private boolean isNew = true;

    /**
     * Returns whether the entity is new or not.
     *
     * @return true if the entity is new, false otherwise
     */
    @JsonIgnore
    @Override
    public boolean isNew() {
        return isNew;
    }

    /**
     * Marks the entity as not new (persisted) after it has been persisted
     * or loaded from the database.
     */
    @PrePersist
    @PostLoad
    void markNotNew() {
        this.isNew = false;
    }

    /**
     * Compares this entity to another entity based on their identifiers.
     *
     * @param obj the object to compare to
     * @return true if the entities have the same identifier, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this == obj) {
            return true;
        } else if (!this.getClass().equals(ProxyUtils.getUserClass(obj))) {
            return false;
        } else {
            AbstractPersistable<?> that = (AbstractPersistable<?>) obj;
            return Objects.equals(getId(), that.getId());
        }
    }

    /**
     * Returns the hash code of the entity based on its identifier.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
