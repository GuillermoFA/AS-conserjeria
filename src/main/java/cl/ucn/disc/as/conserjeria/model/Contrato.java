package cl.ucn.disc.as.conserjeria.model;

import io.ebean.annotation.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

/**
 * Author: Guillermo Fuentes Avila
 * Class: Contrato
 */
@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Contrato extends BaseModel {


    /**
     * The fechaPago
     */
    @NotNull
    @Getter
    private Instant fechaPago;

    /**
     * The persona Class Persona
     */
    @ManyToOne
    @Getter
    private Persona persona;


    /**
     * The departamento Class Departamento
     */
    @ManyToOne
    @Getter
    private Departamento departamento;

    /**
     * List Pagos - Pagos asociados al contrato.
     */
    @OneToMany(mappedBy = "contrato")
    private List<Pago> pagos;

}
