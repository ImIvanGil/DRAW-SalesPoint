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

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import mx.uach.fing.draw.project.salespoint.HibernateUtil;
import mx.uach.fing.draw.project.salespoint.model.SaleOrder;
import mx.uach.fing.draw.project.salespoint.model.User;
import mx.uach.fing.draw.project.salespoint.validator.SaleOrderValidator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

/**
 * Controlador para las ordenes de compra.
 *
 * @author Brian Barron Diaz
 */
public class OrderController extends Controller {

    /**
     * Metodo para obtener los datos de compra.
     *
     * @param request
     * @param response
     * @return Vista de las ordenes de compra.
     */
    public ModelAndView orders(Request request, Response response) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        User user = request.session().attribute("user");

        List<SaleOrder> orders = session.createCriteria(SaleOrder.class)
                .list();

        orders = orders.stream()
                .filter((order) -> Objects.equals(user.getUserId(),
                                order.getUser().getUserId()))
                .collect(Collectors.toList());

        set("orders", orders);

        return new ModelAndView(values(request), "user.ftl");
    }

    /**
     * Metodo para crear una orden de compra.
     *
     * @param request
     * @param response
     * @return
     */
    public Object createOrder(Request request, Response response) {
        String description = request.queryParams("description");

        User user = request.session().attribute("user");

        SaleOrderValidator validator = new SaleOrderValidator(request.session());

        validator.validateDescription(description);

        if (!validator.error()) {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();

            Transaction transaction = session.beginTransaction();

            SaleOrder order = new SaleOrder();
            order.setCreatedAt(new Date());
            order.setStatus("PENDIENTE");
            order.setUser(user);
            order.setDescription(description);

            session.save(order);

            transaction.commit();
            session.close();
        }

        response.redirect("/orders");

        return null;
    }

    /**
     * Metodo para obtener una orden de compra.
     *
     * @param request
     * @param response
     * @return Orden de compra.
     */
    public Object order(Request request, Response response) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Long id = Long.valueOf(request.params("id"));

        SaleOrder order = (SaleOrder) session.createCriteria(SaleOrder.class)
                .add(Restrictions.eq("orderId", id))
                .uniqueResult();

        return order;
    }
}
