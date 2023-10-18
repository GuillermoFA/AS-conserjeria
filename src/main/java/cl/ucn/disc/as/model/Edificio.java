package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Guillermo Fuentes Avila
 * Class: Edificio
 */
@ToString(callSuper = true)
@Getter
@AllArgsConstructor
@Builder
@Entity
public class Edificio extends BaseModel {

    /**
     * The Nombre.
     */
    @NotNull
    private String nombre;

    /**
     * The Direccion.
     */
    @NotNull
    private String direccion;

    /**
     * One edificio pertenece a Many departamentos.
     */
    @OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
    private List<Departamento> departamentos;

    /**
     *
     * @param departamento
     * @return add Departament
     */
    public void addDepartamento(Departamento departamento) {
        // TODO: evitar los duplicados
        for (Departamento depa : this.departamentos) {
            if (depa.getPiso().equals(departamento.getPiso()) && depa.getNumero().equals(departamento.getNumero())) {
                throw new IllegalArgumentException("Edificio ya tiene departamento: " + departamento.getNumero());
            }
        }
        departamentos.add(departamento);
        departamento.setEdificio(this);
    }

}
