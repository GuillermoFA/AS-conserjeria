/*
* Copyright 2023. Arquitectura Software DISC, UCN.
* */

package cl.ucn.disc.as.conserjeria.ui;

import io.javalin.Javalin;


/*
 * The routes Config.
 *
 * @author Guillermo Fuentes
 * */

public interface RoutesConfigurator {
    /**
     * Configure the routes
     *
     * @param javalin to use
     */
    void configure(Javalin javalin);


}
