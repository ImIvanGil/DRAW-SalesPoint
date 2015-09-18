/*
 * Copyright (C) 2015 Luis Chávez Bustamante
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package mx.uach.fing.draw.project.salespoint.validator;

import java.util.List;

import spark.Session;

/**
 * Clase abstracta encargada de manejar las validaciones de los modelos.
 *
 * @author Luis Chávez Bustamante
 */
public abstract class Validator {

    // Sesion de la aplicacion.
    private final Session session;

    // Indica si existen errores.
    private boolean error;

    /**
     * Constructor principal del la clase validator.
     *
     * @param session sesion de la aplicacion.
     */
    public Validator(Session session) {
        this.session = session;
    }

    /**
     * Almacena un error en la sesion.
     *
     * @param message mensaje de error.
     */
    protected void error(String message) {
        if (null != session) {
            List<String> errors = session.attribute("errors");
            errors.add(message);
            session.attribute("errors", errors);
        }
        error = true;
    }

    /**
     * Obtiene el estado del validador.
     *
     * @return true si existe algun error, falso de otro forma.
     */
    public boolean error() {
        return error;
    }
}
