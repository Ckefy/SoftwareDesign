package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.entities.product.dao.ProductDAO;
import ru.akirakozov.sd.refactoring.entities.product.printer.HTMLPrinter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends ProductsServlet {
    public GetProductsServlet(ProductDAO productDAO) {
        super(productDAO);
    }

    @Override
    public void sendRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter printWriter = response.getWriter();

        HTMLPrinter printer = new HTMLPrinter(printWriter);
        printer.printList(productDAO.selectAll());
    }
}
