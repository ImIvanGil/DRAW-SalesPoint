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

import mx.uach.fing.draw.project.salespoint.HibernateUtil;
import mx.uach.fing.draw.project.salespoint.model.SaleOrder;
import mx.uach.fing.draw.project.salespoint.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

/**
 * 
 * @author Luis Chávez Bustamante
 */

//Esta clase le agrega a la aplicacion todas las acciones que puede realizar un administrador
public class AdminController {
   
    //Este metodo le muestra las ordenes de los usuarios al administrador
    public static ModelAndView admin(Request request, Response response) {
        User user = request.session().attribute("user");

        if (!user.getIsAdmin()) {
            response.redirect("/");
        }

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        List<SaleOrder> orders = session.createCriteria(SaleOrder.class)
                .list();

        Map<String, Object> map = new HashMap<>();
        map.put("orders", orders);

        return new ModelAndView(map, "admin.ftl");
    }
    
    //Este metodo le permite al administrador liberar los pedidos
    public static Object updateOrderStatus(Request request, Response response) {
        User user = request.session().attribute("user");

        if (!user.getIsAdmin()) {
            response.redirect("/");
        }

        boolean errors = false;
        Long id = null;
        String status = request.params("status");

        try {
            id = Long.valueOf(request.params("id"));
        } catch (NumberFormatException ex) {
            errors = true;
        }

        if ("LIBERAR".equals(status)) {
            errors = true;
        }

        if (!errors) {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();

            SaleOrder saleOrder = (SaleOrder) session.createCriteria(SaleOrder.class)
                    .add(Restrictions.eq("order_id", id))
                    .uniqueResult();

            Transaction transaction = session.beginTransaction();

            saleOrder.setStatus(status);
            session.update(saleOrder);

            transaction.commit();
            session.close();
        }

        response.redirect("/admin");

        return null;
    }
}