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

import mx.uach.fing.draw.project.salespoint.model.User;
import mx.uach.fing.draw.project.salespoint.validator.UserValidator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Pruebas de las validaciones del modelo de usuario.
 *
 * @author Luis Chávez Bustamante
 */
public class UserValidationTest {

    @Test
    public void testNameWithNumbers() {
        UserValidator validator = new UserValidator(null);

        validator.validateUserName("123");
        assertTrue(validator.error());
    }

    @Test
    public void testNullName() {
        UserValidator validator = new UserValidator(null);

        validator.validateUserName("123");
        assertTrue(validator.error());
    }

    @Test
    public void testEmptyName() {
        UserValidator validator = new UserValidator(null);

        validator.validateUserName("");
        assertTrue(validator.error());
    }

    @Test
    public void testLargeName() {
        UserValidator validator = new UserValidator(null);

        validator.validateUserName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertTrue(validator.error());
    }

    @Test
    public void testValidName() {
        UserValidator validator = new UserValidator(null);

        validator.validateUserName("Luis");
        assertFalse(validator.error());
    }

    @Test
    public void testLastNameWithNumbers() {
        UserValidator validator = new UserValidator(null);

        validator.validateUserLastName("123");
        assertTrue(validator.error());
    }

    @Test
    public void testNullLastName() {
        UserValidator validator = new UserValidator(null);

        validator.validateUserLastName("123");
        assertTrue(validator.error());
    }

    @Test
    public void testEmptyLastName() {
        UserValidator validator = new UserValidator(null);

        validator.validateUserLastName("");
        assertTrue(validator.error());
    }

    @Test
    public void testLargeLastName() {
        UserValidator validator = new UserValidator(null);

        validator.validateUserLastName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertTrue(validator.error());
    }

    @Test
    public void testValidLastName() {
        UserValidator validator = new UserValidator(null);

        validator.validateUserLastName("Chavez");
        assertFalse(validator.error());
    }

    @Test
    public void testSamePassword() {
        UserValidator validator = new UserValidator(null);

        validator.validatePassword("pass123", "pass123");
        assertFalse(validator.error());
    }

    @Test
    public void testDistinctPassword() {
        UserValidator validator = new UserValidator(null);

        validator.validatePassword("pass123", "pass");
        assertTrue(validator.error());
    }

    @Test
    public void testNullUser() {
        UserValidator validator = new UserValidator(null);

        validator.validateUser(null);
        assertTrue(validator.error());
    }

    @Test
    public void testNotNullUser() {
        UserValidator validator = new UserValidator(null);

        validator.validateUser(new User());
        assertFalse(validator.error());
    }
}
