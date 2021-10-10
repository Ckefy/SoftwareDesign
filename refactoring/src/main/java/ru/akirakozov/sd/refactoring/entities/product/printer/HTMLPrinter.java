package ru.akirakozov.sd.refactoring.entities.product.printer;

import ru.akirakozov.sd.refactoring.entities.product.dto.ProductDTO;

import java.io.PrintWriter;
import java.util.ArrayList;

public class HTMLPrinter {
    private final PrintWriter printWriter;

    public HTMLPrinter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void printList(ArrayList<ProductDTO> productList) {
        this.printWriter.println("<html><body>");
        for (ProductDTO product: productList) {
            String name = product.getName();
            int price = product.getPrice();
            this.printWriter.println(name + "\t" + price + "</br>");
        }
        this.printWriter.println("</body></html>");
    }

    public void printWithInfo(ProductDTO product, String info) {
        this.printWriter.println("<html><body>");
        this.printWriter.println("<h1>" + info + "</h1>");
        if (product != null) {
            String name = product.getName();
            int price = product.getPrice();
            this.printWriter.println(name + "\t" + price + "</br>");
        }
        this.printWriter.println("</body></html>");
    }
}
