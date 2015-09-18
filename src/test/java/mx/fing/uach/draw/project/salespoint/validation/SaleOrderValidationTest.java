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
package mx.fing.uach.draw.project.salespoint.validation;

import mx.uach.fing.draw.project.salespoint.validator.SaleOrderValidator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Pruebas de las validaciones del modelo de ordenes.
 *
 * @author Luis Chávez Bustamante
 */
public class SaleOrderValidationTest {

    @Test
    public void validateNullDescription() {
        SaleOrderValidator validator = new SaleOrderValidator(null);

        validator.validateDescription(null);
        assertTrue(validator.error());
    }

    @Test
    public void validateEmptyDescription() {
        SaleOrderValidator validator = new SaleOrderValidator(null);

        validator.validateDescription("");
        assertTrue(validator.error());
    }

    @Test
    public void validateValidDescription() {
        SaleOrderValidator validator = new SaleOrderValidator(null);

        validator.validateDescription("Descripcion de la orden.");
        assertFalse(validator.error());
    }
}
