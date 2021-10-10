import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.akirakozov.sd.refactoring.entities.product.dao.ProductDAO;
import ru.akirakozov.sd.refactoring.entities.product.dto.ProductDTO;
import ru.akirakozov.sd.refactoring.servlet.GetProductsServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetProductsServletTest {
    private GetProductsServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ProductDAO productDAO;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        servlet = new GetProductsServlet(productDAO);
    }

    @Test
    public void getProductsTest() throws IOException, SQLException {
        ArrayList<ProductDTO> mockList = new ArrayList<>();
        mockList.add(new ProductDTO("Billy Herrington", 1050));
        mockList.add(new ProductDTO("Van DarkHolme", 300));
        mockList.add(new ProductDTO("Naruto Boy", 10));
        mockList.add(new ProductDTO("Anime Cool", 30000));
        mockList.add(new ProductDTO("Test Testov", 1000000));
        mockList.add(new ProductDTO("Qwerty Tarantino", 250000));

        Mockito.when(productDAO.selectAll())
                .thenReturn(mockList);
        StringWriter out = new StringWriter();
        PrintWriter printWriter = new PrintWriter(out);
        Mockito.when(response.getWriter())
                .thenReturn(printWriter);

        servlet.doGet(request, response);

        printWriter.flush();
        StringBuilder htmlExpected = new StringBuilder();
        htmlExpected.append("<html><body>").append(System.lineSeparator())
                    .append("Billy Herrington\t1050</br>").append(System.lineSeparator())
                    .append("Van DarkHolme\t300</br>").append(System.lineSeparator())
                    .append("Naruto Boy\t10</br>").append(System.lineSeparator())
                    .append("Anime Cool\t30000</br>").append(System.lineSeparator())
                    .append("Test Testov\t1000000</br>").append(System.lineSeparator())
                    .append("Qwerty Tarantino\t250000</br>").append(System.lineSeparator())
                    .append("</body></html>").append(System.lineSeparator());
        Assert.assertEquals(htmlExpected.toString(), out.toString());
    }
}
