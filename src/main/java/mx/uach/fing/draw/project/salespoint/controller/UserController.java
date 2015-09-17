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

import mx.uach.fing.draw.project.salespoint.HibernateUtil;
import mx.uach.fing.draw.project.salespoint.model.User;
import mx.uach.fing.draw.project.salespoint.validator.UserValidator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import spark.Request;
import spark.Response;

/**
 * Controlador para manejar el modelo de Usuario.
 * 
 * @author Luis Chávez Bustamante
 */
public class UserController {
    
    /**
     * Metodo para cerrar la sesion del usuarios.
     * 
     * @param request
     * @param response
     * @return 
     */
    public static Object doLogout(Request request, Response response) {
        request.session().removeAttribute("user");
        response.redirect("/");
        return null;
    }
    
    /**
     * Metodo para inicial la sesion del usuario.
     * 
     * @param request
     * @param response
     * @return 
     */
    public static Object doLogin(Request request, Response response) {
        String nickname = request.queryParams("nickname");
        String password = request.queryParams("password");
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("nickname", nickname))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
        
        if (null != user) {
            request.session(true);
            request.session().attribute("user", user);
            response.redirect("/orders");
        } else {
            response.redirect("/");
        }
        
        return null;
    }

    /**
     * Metodo para registrar a un usuario.
     * 
     * @param request
     * @param response
     * @return 
     */
    public static Object doSignup(Request request, Response response) {
        String name = request.queryParams("name");
        String lastName = request.queryParams("last_name");
        String nickname = request.queryParams("nickname");
        String password = request.queryParams("password");
        String confirmPassword = request.queryParams("confirm_password");

        UserValidator validator = new UserValidator(request.session());

        validator.validateUserName(name);
        validator.validateUserLastName(lastName);
        validator.validatePassword(password, confirmPassword);

        if (!validator.error()) {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();

            Transaction transaction = session.beginTransaction();

            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setNickname(nickname);
            user.setPassword(password);
            
            session.save(user);

            transaction.commit();
            session.close();
            
            UserController.doLogin(request, response);
        } else {
            response.redirect("/do_signup");
        }

        return null;
    }
}
