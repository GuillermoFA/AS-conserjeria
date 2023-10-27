/*
 * Copyright (c) 2023. Arquitectura de Sistemas, DISC, UCN.
 */

package cl.ucn.disc.as.conserjeria.model;

import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.Instant;

/**
 * Base Class.
 *
 * @author Guillermo Fuentes Avila.
 */
@Getter
@ToString
@MappedSuperclass
public abstract class BaseModel {

    /**
     * The Id.
     */
    @Setter
    @Id
    private Long id;

    /**
     * The Version.
     */
    @Setter
    @Version
    private Long version;

    /**
     * Creation date.
     */
    @Setter
    @WhenCreated
    private Instant created;

    /**
     * Modified date.
     */
    @Setter
    @WhenModified
    private Instant modified;

}
