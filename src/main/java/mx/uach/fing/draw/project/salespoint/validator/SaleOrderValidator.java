/*
 * Copyright (C) 2015 Brian Barron Diaz
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

import spark.Session;

/**
 * Clase para validar las ordenes de compra.
 * 
 * @author Brian Barron Diaz
 */
public class SaleOrderValidator extends Validator {

    /**
     * Contructor de la clase.
     * @param session 
     */
    public SaleOrderValidator(Session session) {
        super(session);
    }

    /**
     * Metodo para validar la descripcion.
     * 
     * @param description Descripcion de la orden de compra.
     */
    public void validateDescription(String description) {
        if (null == description || description.isEmpty()) {
            error("La descripcion no es valida.");
        }
    }
}
