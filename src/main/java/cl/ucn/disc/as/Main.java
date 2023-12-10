package cl.ucn.disc.as;

import cl.ucn.disc.as.conserjeria.grpc.PersonaGrpcServiceImpl;
import cl.ucn.disc.as.conserjeria.ui.ApiRestServer;
import cl.ucn.disc.as.conserjeria.ui.WebController;
import io.grpc.*;
import lombok.extern.slf4j.Slf4j;
import io.javalin.Javalin;

import java.io.IOException;

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
    public static void main(String[] args) throws IOException, InterruptedException {
        log.debug("Starting Main ..");

        log.debug("Library path: {}", System.getProperty("java.library.path"));

        //Start the API Rest server
        Javalin app = ApiRestServer.start(7070, new WebController());

        // Start the gRPC server
        log.debug("Starting the gRPC server ..");

        Server server = ServerBuilder
                .forPort(50123)
                .addService(new PersonaGrpcServiceImpl())
                .build();


        server.start();
        // Shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
        // wait for the stop
        server.awaitTermination();
        log.debug("Done. :)");

    }
}
