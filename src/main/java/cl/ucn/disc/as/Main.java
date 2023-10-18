package cl.ucn.disc.as;


import cl.ucn.disc.as.model.*;
import cl.ucn.disc.as.services.Sistema;
import cl.ucn.disc.as.services.SistemaImpl;
import io.ebean.DB;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.List;


/**
 *
 * The Main
 * @author Guillermo Fuentes
 *
 * */

@Slf4j
public final class Main {

    /**
     * The Main.
     * @param args to use.
     */
    public static void main(String[] args) {

        log.debug("Starting Main..");

        // Base de datos
        Sistema sistema = new SistemaImpl(DB.getDefault());

        //Creacion de persona en la base de datos
        Persona persona = Persona.builder()
                .rut("20416349-9")
                .nombre("Guillermo")
                .apellidos("Fuentes Ávila")
                .email("guillermo.fuentes01@alumnos.ucn.cl")
                .telefono("+5691234567")
                .build();
        log.debug("The persona before bd: ${}", persona);
        persona = sistema.add(persona);
        log.debug("The persona after bd: ${}", persona);

        //Creacion de edificio en la base de datos
        Edificio edificio = Edificio.builder()
                .nombre("LagunasBuild")
                .direccion("Costa Laguna")
                .build();
        log.debug("Edificio before db: ${}", edificio);
        edificio = sistema.add(edificio);
        log.debug("Edificio after db: {}",edificio);

        // Crear departamento y asociarlo a un edificio
        Departamento departamento1 = Departamento.builder()
                .piso(15)
                .numero(1501)
                .build();
        log.debug("Departamento before db: ${}", departamento1);
        departamento1 = sistema.addDepartamento(departamento1, edificio);
        log.debug("Departamento after db: {}", departamento1);
        log.debug("Edificio after addDepartamento Objeto: {}", edificio.getDepartamentos());

        // Crear un departamento y asociarlo a un edificio mediante la del edificio.
        Departamento departamento2 = Departamento.builder()
                .piso(8)
                .numero(805)
                .build();
        log.debug("Departamento before db: ${}", departamento2);
        departamento2 = sistema.addDepartamento(departamento2, edificio.getId());
        log.debug("Departamento after db: {}",departamento2);
        log.debug("Edificio after addDepartamento Long: {}",edificio);

        //  Realizar un contrato PERSONA - DEPARTAMENTO1.
        Instant fechaPago = Instant.now();
        Contrato contrato = sistema.realizarContrato(persona,departamento1,fechaPago);
        Pago pago = Pago.builder().monto(150000).fechaPago(fechaPago).contrato(contrato).build();
        log.debug("Pago before contrato: {}",pago);
        pago = sistema.add(pago);
        log.debug("Pago after contrato: {}",pago);


        // Obtener contratos
        List<Contrato> contratosList = sistema.getContratos();
        log.debug("Contrato" + contratosList);

        // Obtener personas
        List<Persona> personasList = sistema.getPersonas();
        log.debug("Contrato" + personasList);


        log.debug("Done.");
        /*
        Long idDuenio = persona.getId();
        Long idDepartamento = departamento1.getId();
        Contrato contrato1 = sistema.realizarContrato1(idDuenio,idDepartamento,fechaPago);
        Pago pago1 = Pago.builder().monto(150000).fechaPago(fechaPago).contrato(contrato1).build();
        log.debug("Pago before contrato: {}",pago1);
        pago1 = sistema.add(pago);
        log.debug("Pago after contrato: {}",pago1)

        // Obtener pagos con el rut
        List<Pago> pagosList = sistema.getPagos(persona.getRut());
        log.debug("La persona: " + persona.getNombre() + " de rut : " + persona.getRut() + " Ha realizado los siguientes pagos --> " + pagosList);
         */
    }
}
