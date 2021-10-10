package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.entities.product.dao.ProductDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ProductsServlet extends HttpServlet {
    protected final ProductDAO productDAO;

    public ProductsServlet(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public abstract void sendRequest(HttpServletRequest request, HttpServletResponse response) throws Exception;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            sendRequest(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
