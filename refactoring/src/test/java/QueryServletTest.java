import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.akirakozov.sd.refactoring.entities.product.dao.ProductDAO;
import ru.akirakozov.sd.refactoring.entities.product.dto.ProductDTO;
import ru.akirakozov.sd.refactoring.servlet.QueryServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

public class QueryServletTest {
    private QueryServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ProductDAO productDAO;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        servlet = new QueryServlet(productDAO);
    }

    @Test
    public void maxTest() throws IOException, SQLException {
        Mockito.when(request.getParameter("command"))
                .thenReturn("max");
        Mockito.when(productDAO.getProductMaxPrice())
                .thenReturn(new ProductDTO("Billy Herrington", 1050));
        StringWriter out = new StringWriter();
        PrintWriter printWriter = new PrintWriter(out);
        Mockito.when(response.getWriter())
                .thenReturn(printWriter);

        servlet.doGet(request, response);

        printWriter.flush();
        StringBuilder htmlExpected = new StringBuilder();
        htmlExpected.append("<html><body>").append(System.lineSeparator())
                .append("<h1>Product with max price: </h1>").append(System.lineSeparator())
                .append("Billy Herrington\t1050</br>").append(System.lineSeparator())
                .append("</body></html>").append(System.lineSeparator());
        Assert.assertEquals(htmlExpected.toString(), out.toString());
    }

    @Test
    public void minTest() throws IOException, SQLException {
        Mockito.when(request.getParameter("command"))
                .thenReturn("min");
        Mockito.when(productDAO.getProductMinPrice())
                .thenReturn(new ProductDTO("Van DarkHolme", 155000));
        StringWriter out = new StringWriter();
        PrintWriter printWriter = new PrintWriter(out);
        Mockito.when(response.getWriter())
                .thenReturn(printWriter);

        servlet.doGet(request, response);

        printWriter.flush();
        StringBuilder htmlExpected = new StringBuilder();
        htmlExpected.append("<html><body>").append(System.lineSeparator())
                .append("<h1>Product with min price: </h1>").append(System.lineSeparator())
                .append("Van DarkHolme\t155000</br>").append(System.lineSeparator())
                .append("</body></html>").append(System.lineSeparator());
        Assert.assertEquals(htmlExpected.toString(), out.toString());
    }

    @Test
    public void sumTest() throws IOException, SQLException {
        Mockito.when(request.getParameter("command"))
                .thenReturn("sum");
        Mockito.when(productDAO.getSum())
                .thenReturn((long) 250000);
        StringWriter out = new StringWriter();
        PrintWriter printWriter = new PrintWriter(out);
        Mockito.when(response.getWriter())
                .thenReturn(printWriter);

        servlet.doGet(request, response);

        printWriter.flush();
        StringBuilder htmlExpected = new StringBuilder();
        htmlExpected.append("<html><body>").append(System.lineSeparator())
                .append("Summary price: ").append(System.lineSeparator())
                .append("250000").append(System.lineSeparator())
                .append("</body></html>").append(System.lineSeparator());
        Assert.assertEquals(htmlExpected.toString(), out.toString());
    }

    @Test
    public void countTest() throws IOException, SQLException {
        Mockito.when(request.getParameter("command"))
                .thenReturn("count");
        Mockito.when(productDAO.getCount())
                .thenReturn(100);
        StringWriter out = new StringWriter();
        PrintWriter printWriter = new PrintWriter(out);
        Mockito.when(response.getWriter())
                .thenReturn(printWriter);

        servlet.doGet(request, response);

        printWriter.flush();
        StringBuilder htmlExpected = new StringBuilder();
        htmlExpected.append("<html><body>").append(System.lineSeparator())
                .append("Number of products: ").append(System.lineSeparator())
                .append("100").append(System.lineSeparator())
                .append("</body></html>").append(System.lineSeparator());
        Assert.assertEquals(htmlExpected.toString(), out.toString());
    }
}
