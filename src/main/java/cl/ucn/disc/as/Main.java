package cl.ucn.disc.as;


import lombok.extern.slf4j.Slf4j;
import cl.ucn.disc.as.conserjeria.ui.WebController;
import cl.ucn.disc.as.conserjeria.ui.ApiRestServer;
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
        log.debug("Starting Main .. ");
        ApiRestServer.start(7070, new WebController());

        log.debug("Done. : ");

    }
}
