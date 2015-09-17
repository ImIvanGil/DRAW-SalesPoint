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

import mx.uach.fing.draw.project.salespoint.controller.AdminController;
import mx.uach.fing.draw.project.salespoint.controller.HomeController;
import mx.uach.fing.draw.project.salespoint.controller.OrderController;
import mx.uach.fing.draw.project.salespoint.controller.UserController;
import mx.uach.fing.draw.project.salespoint.filter.ErrorFilter;
import mx.uach.fing.draw.project.salespoint.transform.JsonTransformer;

import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.*;

/**
 * Clase principal.
 *
 * @author Luis Chávez Bustamante
 */
public class Main {

    /**
     * Configuracion global de la aplicacion.
     */
    public void initialize() {
        // Ruta de los archivos estaticos.
        staticFileLocation("/");
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

        // Motor de plantillas.
        FreeMarkerEngine engine = new FreeMarkerEngine(configuration);

        // Maneja los errores de la sesion.
        before(ErrorFilter.CREATE_ERRORS);

        // Controladores.
        AdminController adminController = new AdminController();
        HomeController homeController = new HomeController();
        OrderController orderController = new OrderController();
        UserController userController = new UserController();
        
        // Crea la cuenta de administrador.
        adminController.createDefaultAdminUser();

        // Ruta a la pagina principal.
        get("/", homeController::index, engine);
        // Ruta para autentificar al usuario.
        post("/do_login", userController::doLogin);
        // Ruta para terminar la sesion.
        get("/do_logout", userController::doLogout);
        // Ruta para registrar a un usuario.
        post("/do_signup", userController::doSignup);
        // Ruta para mostrar las ordenes de compra del usuario.
        get("/orders", orderController::orders, engine);
        // Ruta para crear una nueva orden.
        post("/create_order", orderController::createOrder);
        // Ruta para la seccion de administracion.
        get("/admin", adminController::admin, engine);
        // Ruta (AJAX) para obtener la informacion de un pedido.
        get("/order/:id", orderController::order, new JsonTransformer());
        // Ruta para actualizar el estado de un pedido.
        get("/do_status/:id/:status", adminController::updateOrderStatus);
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
    }
}
