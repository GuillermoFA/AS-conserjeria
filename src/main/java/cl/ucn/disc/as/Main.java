package cl.ucn.disc.as;


import lombok.extern.slf4j.Slf4j;
import cl.ucn.disc.as.conserjeria.ui.WebController;
import cl.ucn.disc.as.conserjeria.ui.ApiRestServer;
import io.javalin.Javalin;
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
        log.debug("Starting Main ..");

        log.debug("Library path: {}", System.getProperty("java.library.path"));

        //Start the API Rest server
        Javalin app = ApiRestServer.start(7070, new WebController());

        app.stop();
        log.debug("Done. :)");

    }
}
