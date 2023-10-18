package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.Instant;



/**
 * Class Pago
 *
 * @author Guillermo Fuentes Avila
 */
@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Pago extends BaseModel {

    /**
     * The fechaPago
     */
    @Getter
    @NotNull
    private Instant fechaPago;

    /**
     * The monto
     */
    @Getter
    @NotNull
    private Integer monto;

    /**
     * The contrato
     */
    @ManyToOne
    private Contrato contrato;

}
