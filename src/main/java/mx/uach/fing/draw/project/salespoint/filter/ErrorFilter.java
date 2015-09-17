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
package mx.uach.fing.draw.project.salespoint.filter;

import java.util.ArrayList;
import java.util.List;

import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * Filtros para manejar los errores de los formularios.
 *
 * @author Luis Chávez Bustamante
 */
public class ErrorFilter {

    public static final CreateErrors CREATE_ERRORS = new CreateErrors();

    private ErrorFilter() {
    }

    /**
     * Filtro para crear un arreglo de errores.
     */
    public static class CreateErrors implements Filter {

        @Override
        public void handle(Request request, Response response)
                throws Exception {
            List<String> errors = request.session().attribute("errors");
            if (null == errors) {
                request.session().attribute("errors", new ArrayList<>());
            }
        }
    }
}
