package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.entities.product.dao.ProductDAO;
import ru.akirakozov.sd.refactoring.entities.product.dto.ProductDTO;
import ru.akirakozov.sd.refactoring.entities.product.printer.HTMLPrinter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author akirakozov
 */
public class QueryServlet extends ProductsServlet {

    public QueryServlet(ProductDAO productDAO) {
        super(productDAO);
    }

    @Override
    public void sendRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String command = request.getParameter("command");
        PrintWriter printWriter = response.getWriter();

        HTMLPrinter printer = new HTMLPrinter(printWriter);
        if ("max".equals(command)) {
            ProductDTO productMaxPrice = productDAO.getProductMaxPrice();
            printer.printProductFull(productMaxPrice, "Product with max price: ");
        } else if ("min".equals(command)) {
            ProductDTO productMinPrice = productDAO.getProductMinPrice();
            printer.printProductFull(productMinPrice, "Product with min price: ");
        } else if ("sum".equals(command)) {
            long sum = productDAO.getSum();
            printer.printProductInfo(sum, "Summary price: ");
        } else if ("count".equals(command)) {
            int count = productDAO.getCount();
            printer.printProductInfo(count, "Number of products: ");
        } else {
            printWriter.println("Unknown command: " + command);
        }
    }
}
