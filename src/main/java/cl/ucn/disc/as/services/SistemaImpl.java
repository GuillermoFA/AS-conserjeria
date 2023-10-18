package cl.ucn.disc.as.services;

import cl.ucn.disc.as.exceptions.IllegalDomainException;
import cl.ucn.disc.as.exceptions.SistemaException;
import cl.ucn.disc.as.model.Contrato;
import cl.ucn.disc.as.model.Departamento;
import cl.ucn.disc.as.model.Edificio;
import cl.ucn.disc.as.model.Pago;
import cl.ucn.disc.as.model.Persona;
import io.ebean.Database;
import io.ebean.Query;
import java.time.Instant;
import java.util.List;
import javax.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;


/**
 * Sistema implementación.
 *
 * @author Guillermo Fuentes
 */
@Slf4j
public class SistemaImpl implements Sistema {


    /**
     * The database of the system.
     */
    private final Database database;

    /**
     * The Constructor.
     */
    public SistemaImpl(Database database) {

        this.database = database;
    }

    @Override
    public Pago add(Pago pago){
        try{
            this.database.save(pago);
        }catch(PersistenceException exception){
            log.error("Error", exception);
            throw new SistemaException("Error al agregar un pago", exception);
        }
        return pago;
    }

    /**
     * Edificio add Se agrega un edificio al sistema
     */

    @Override
    public Edificio add(@NotNull Edificio edificio) {
        try {
            this.database.save(edificio);
        }catch(PersistenceException ex){
            //TODO: save the exception
            log.error("Error ",ex);
            throw new SistemaException("Error al agregar un edificio", ex);
        }
        //WARN: need to retrieve the id?
        return edificio;
    }

    /**
     * Persona add agrega una persona al sistema
     */
    @Override
    public Persona add(Persona persona) {
        try {
            this.database.save(persona);
        } catch (PersistenceException ex) {
            //TODO: save the exception
            log.error("Error ", ex);
            throw new SistemaException("Error al agregar una persona", ex);
        }
        return persona;
    }

    /**
     * Agrega un departamento a un edificio
     */
    @Override
    public Departamento addDepartamento(Departamento departamento, Edificio edificio) {

        log.debug("Adding {} to {}.", departamento, edificio);
        try {
            edificio.addDepartamento(departamento);
            this.database.save(edificio);
        } catch (PersistenceException ex) {
            //TODO: save the exception
            log.error("Error ", ex);
            throw new SistemaException("Error al agregar un departamento", ex);
        }

        return departamento;
    }

    /**
     * Agrega un departamento a una id de un edificio
     */
    @Override
    public Departamento addDepartamento(Departamento departamento, Long idEdificio) {

        Edificio edificio = this.database.find(Edificio.class, idEdificio);
        if (edificio == null) {
            throw new IllegalDomainException("El edificio con ID " + idEdificio + " no existe");
        }
        return this.addDepartamento(departamento, edificio);

    }

    /**
     * Realiza un contrato
     */
    @Override
    public Contrato realizarContrato(Persona duenio, Departamento departamento, Instant fechaPago) {
        Contrato contrato = Contrato.builder()
                .fechaPago(fechaPago)
                .persona(duenio)
                .departamento(departamento)
                .build();
        try {
            this.database.save(contrato);
        } catch (PersistenceException ex) {
            //TODO: save the exception
            log.error("Error al guardar el contrato", ex);
            throw new SistemaException("Error al realizar un contrato", ex);
        }

        return contrato;
    }


    /*
        /**
           * Realiza un contrato por id

        @Override
    public Contrato realizarContrato1(Long idDuenio, Long idDepartamento, Instant fechaPago) {

        try {
            Persona duenio = this.database.find(Persona.class, idDuenio);
            Departamento departamento = this.database.find(Departamento.class, idDepartamento);

            if (duenio == null || departamento == null) {
                throw new IllegalDomainException("No se encontró la persona o el departamento");
            }

            return this.realizarContrato(duenio, departamento, fechaPago);
        } catch (NullPointerException e) {
             //Manejo de la NullPointerException
            System.err.println("Se ha producido una NullPointerException: " + e.getMessage());
            return null; // O devuelve un valor predeterminado
        }
    }
     */


    /**
     * Obtiene una lista de contratos
     */
    @Override
    public List<Contrato> getContratos() {
        try {
            Query<Contrato> query = database.find(Contrato.class);
            return query.findList();
        } catch (PersistenceException ex) {
            // Manejar la excepción de persistencia.
            log.error("Error al recuperar contratos", ex);
            throw new SistemaException("Error al obtener la lista de contratos", ex);
        }
    }

    /**
     * Obtiene una lista de personas
     */
    @Override
    public List<Persona> getPersonas() {
        try {
            Query<Persona> query = database.find(Persona.class);
            return query.findList();
        } catch (PersistenceException ex) {
            // Manejar la excepción de persistencia.
            log.error("Error al recuperar personas", ex);
            throw new SistemaException("Error al obtener la lista de personas", ex);
        }
    }

    /**
     * Obtiene una lista de pagos ingresando el rut
     */
    @Override
    public List<Pago> getPagos(String rut) {
        Query<Pago> query = database.find(Pago.class)
                .where()
                .eq("persona.rut", rut)
                .query();
        return query.findList();
    }
}

