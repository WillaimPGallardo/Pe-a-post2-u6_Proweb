package com.universidad.mvc.controller;

import com.universidad.mvc.model.Producto;
import com.universidad.mvc.service.ProductoService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {

    private ProductoService service = new ProductoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!validarSesion(req, resp)) return;

        String accion = req.getParameter("accion");

        if (accion == null) accion = "listar";

        switch (accion) {

            case "nuevo":
                req.getRequestDispatcher("/WEB-INF/views/formulario.jsp").forward(req, resp);
                break;

            case "editar":
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("producto", service.buscar(id));
                req.getRequestDispatcher("/WEB-INF/views/formulario.jsp").forward(req, resp);
                break;

            case "eliminar":
                service.eliminar(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("productos");
                break;

            default:
                req.setAttribute("lista", service.listar());
                req.getRequestDispatcher("/WEB-INF/views/lista.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!validarSesion(req, resp)) return;

        try {
            int id = req.getParameter("id") == null || req.getParameter("id").isEmpty()
                    ? 0 : Integer.parseInt(req.getParameter("id"));

            Producto p = new Producto(
                    id,
                    req.getParameter("nombre"),
                    req.getParameter("categoria"),
                    Double.parseDouble(req.getParameter("precio")),
                    Integer.parseInt(req.getParameter("stock"))
            );

            if (id == 0) {
                service.guardar(p);
            } else {
                service.actualizar(p);
            }

            resp.sendRedirect("productos");

        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
    }

    private boolean validarSesion(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        HttpSession s = req.getSession(false);

        if (s == null || s.getAttribute("usuarioActual") == null) {
            resp.sendRedirect("login");
            return false;
        }

        return true;
    }
}