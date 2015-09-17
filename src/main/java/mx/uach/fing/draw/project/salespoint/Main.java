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
package mx.uach.fing.draw.project.salespoint;

import freemarker.template.Configuration;

import spark.template.freemarker.FreeMarkerEngine;

import mx.uach.fing.draw.project.salespoint.controller.HomeController;
import mx.uach.fing.draw.project.salespoint.controller.UserController;
import mx.uach.fing.draw.project.salespoint.filter.ErrorFilter;

import static spark.Spark.*;

/**
 *
 * @author Luis Chávez Bustamante
 */
public class Main {

    /**
     * Configuracion global de la aplicacion.
     */
    public void initialize() {
        // Puerto que utiliza la aplicacion.
        port(80);
    }

    /**
     * Configuracion de las rutas de la aplicacion.
     *
     * verbos: - GET - POST - PUT - DELETE
     */
    public void routes() {
        // Configuracion de freemarker.
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(Main.class, "/templates/");

        before(new ErrorFilter.Before());

        // Ruta a la pagina principal.
        get("/", HomeController::index, new FreeMarkerEngine(configuration));
        // Ruta para autentificar al usuario.
        post("/do_login", UserController::doLogin);
        // Ruta para terminar la sesion.
        get("/do_logout", UserController::doLogout);
        // Ruta para registrar a un usuario.
        post("/do_signup", UserController::doSignup);
        // Ruta para mostrar las ordenes de compra del usuario.
        get("/orders", null);
        // Ruta para crear una nueva orden.
        post("/create_order", null);
        // Ruta para la seccion de administracion.
        get("/admin", null);
        // Ruta (AJAX) para obtener la informacion de un pedido.
        get("/order/:id", null);
        // Ruta para actualizar el estado de un pedido.
        get("/do_status/:id/:status", null);
    }

    /**
     * Punto de entrada de la aplicacion.
     *
     * @param args argumentos para inicializar la aplicacion.
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.initialize();
        main.routes();
        /*SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        
         Session session = sessionFactory.openSession();
        
         User user = new User();
         user.setName("Luis");
         user.setLastName("Chávez");
         user.setNickname("luischavez");
         user.setPassword("test");
         user.setIsAdmin(true);
        
         Transaction transaction = session.beginTransaction();
        
         session.save(user);
 
         transaction.commit();
        
         session.close();*/
    }
}
