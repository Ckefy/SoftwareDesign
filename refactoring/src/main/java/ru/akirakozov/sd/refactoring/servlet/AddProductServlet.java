package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.entities.product.dao.ProductDAO;
import ru.akirakozov.sd.refactoring.entities.product.dto.ProductDTO;
import ru.akirakozov.sd.refactoring.entities.product.printer.HTMLPrinter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author akirakozov
 */
public class AddProductServlet extends ProductsServlet {
    public AddProductServlet(ProductDAO productDAO) {
        super(productDAO);
    }

    @Override
    public void sendRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        long price = Long.parseLong(request.getParameter("price"));
        PrintWriter printWriter = response.getWriter();


        productDAO.insert(new ProductDTO(name, price));
        printWriter.println("OK");
    }
}
