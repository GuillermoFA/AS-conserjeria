package cl.ucn.disc.as.services;

import cl.ucn.disc.as.model.*;

import java.time.Instant;
import java.util.List;

/**
 * System operations.
 *
 * @author Arquitectura de Software
 */

public interface Sistema {
    /**
     * Agrega un pago al sistema
     * @param pago a agregar
     */
    Pago add(Pago pago);

    /**
     * Agrega un edificio al Sistema
     * @param edificio a agregar
     */
    Edificio add(Edificio edificio);

    /**
     * Agrega una Persona al sistema
     * @param persona a agregar
     */
    Persona add(Persona persona);

    /**
     * Agrega un departamento al edificio
     * @param departamento a agregar y vincular con edificio
     */
    Departamento addDepartamento(Departamento departamento, Edificio edificio);

    /**
     * Agrega un departamento al sistema
     * @param departamento a agregar con el id del edificio
     */
    Departamento addDepartamento(Departamento departamento, Long idEdificio);

    /**
     * Realiza un contrato
     * @param duenio del departamento, Departamento que pertenece, fecha del pago.
     */
    Contrato realizarContrato(Persona duenio, Departamento departamento, Instant fechaPago);


    /*
         //* Realiza un contrato
     * @param idDuenio la id del dueño, el id del departamento y la fecha de pago.
    Contrato realizarContrato1(Long idDuenio, Long idDepartamento, Instant fechaPago);
     */


    /**
     * Obtiene todos los contratos del sistema
     */
    List<Contrato> getContratos();

    /**
     * Obtiene todas las personas del sistema
     */
    List<Persona> getPersonas();

    /**
     * Obtiene Obtiene los pagos de una persona por su rut
     * @param rut el rut de la persona
     */
    List<Pago> getPagos(String rut);
}
