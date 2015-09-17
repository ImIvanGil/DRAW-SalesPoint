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
package mx.uach.fing.draw.project.salespoint.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.Request;

/**
 * Clase abstracta con metodos pre-definidos para manejar las variables de la
 * vista.
 *
 * @author Luis Chávez Bustamante
 */
public abstract class Controller {

    // Mapa con las variables a mostrar en la vista.
    private final Map<String, Object> map = new HashMap<>();

    protected void set(String key, Object value) {
        map.put(key, value);
    }

    protected Map<String, Object> values(Request request) {
        map.put("session", request.session());
        List<String> errors = request.session().attribute("errors");
        request.session().removeAttribute("errors");
        map.put("errors", errors);
        map.put("user", request.session().attribute("user"));
        return map;
    }
}
