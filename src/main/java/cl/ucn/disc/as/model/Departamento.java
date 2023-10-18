package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Author: Guillermo Fuentes Avila
 * Class: Departamento
 */
@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
@Getter
public class Departamento extends BaseModel {

    /**
     * The number of departament
     */
    @NotNull
    private Integer numero;

    /**
     * The floor of departament
     */
    @NotNull
    private Integer piso;

    /**
     * edificio class Edificio
     */
    @ToString.Exclude
    @ManyToOne
    @Setter
    private Edificio edificio;

}
