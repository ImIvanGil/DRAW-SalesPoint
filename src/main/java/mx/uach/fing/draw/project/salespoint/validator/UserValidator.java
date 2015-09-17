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

import mx.uach.fing.draw.project.salespoint.HibernateUtil;
import mx.uach.fing.draw.project.salespoint.model.User;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import spark.Session;

/**
 * Validador para el modelo de usuario.
 *
 * @author Luis Chávez Bustamante
 */
public class UserValidator extends Validator {

    /**
     * Constructor por defecto.
     *
     * @param session sesion de la aplicacion.
     */
    public UserValidator(Session session) {
        super(session);
    }

    /**
     * Valida el nombre del usuario.
     *
     * @param name nombre del usuario.
     */
    public void validateUserName(String name) {
        if (null == name || name.isEmpty() || 50 < name.length()) {
            error("El nombre no es valido.");
        } else {
            for (char c : name.toCharArray()) {
                if (!Character.isLetter(c)) {
                    error("El nombre solo puede contener letras.");
                }
            }
        }
    }

    /**
     * Valida el apellido del usuario.
     *
     * @param lastName apellido del usuario.
     */
    public void validateUserLastName(String lastName) {
        if (null == lastName || lastName.isEmpty() || 50 < lastName.length()) {
            error("El apellido no es valido.");
        } else {
            for (char c : lastName.toCharArray()) {
                if (!Character.isLetter(c)) {
                    error("El apellido solo puede contener letras.");
                }
            }
        }
    }

    public void validateNickname(String nickname) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        org.hibernate.Session session = sessionFactory.openSession();

        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("nickname", nickname))
                .uniqueResult();
        if (null != user) {
            error("El usuario ya existe.");
        }
    }

    /**
     * Valida la contraseña del usuario.
     *
     * @param password contraseña del usuario.
     * @param confirmPassword confirmacion de la contraseña.
     */
    public void validatePassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            error("Las contraseñas nos coinciden.");
        }
    }

    /**
     * Valilda que el usuario exista.
     *
     * @param user usuario a validar.
     */
    public void validateUser(User user) {
        if (null == user) {
            error("El usuario no existe.");
        }
    }
}
