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

import java.util.List;

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
public class AdminController extends Controller {

    /**
     * Crea la cuenta de administrador solo si no existe.
     */
    public void createDefaultAdminUser() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        User admin = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("nickname", "admin"))
                .uniqueResult();

        if (null == admin) {
            Transaction transaction = session.beginTransaction();

            User user = new User();
            user.setName("admin");
            user.setLastName("admin");
            user.setNickname("admin");
            user.setPassword("admin");
            user.setIsAdmin(true);

            session.save(user);

            transaction.commit();
            session.close();
        }
    }

    //Este metodo le muestra las ordenes de los usuarios al administrador
    public ModelAndView admin(Request request, Response response) {
        User user = request.session().attribute("user");

        if (null == user || !user.getIsAdmin()) {
            response.redirect("/");
        }

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        List<SaleOrder> orders = session.createCriteria(SaleOrder.class)
                .list();

        set("orders", orders);

        return new ModelAndView(values(request), "admin.ftl");
    }

    //Este metodo le permite al administrador liberar los pedidos
    public Object updateOrderStatus(Request request, Response response) {
        User user = request.session().attribute("user");

        if (null == user || !user.getIsAdmin()) {
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

        if (!"LIBERAR".equals(status)) {
            errors = true;
        }

        if (!errors) {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();

            SaleOrder saleOrder = (SaleOrder) session.createCriteria(SaleOrder.class)
                    .add(Restrictions.eq("orderId", id))
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
