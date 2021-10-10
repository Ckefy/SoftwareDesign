import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.akirakozov.sd.refactoring.entities.product.dao.ProductDAO;
import ru.akirakozov.sd.refactoring.entities.product.dto.ProductDTO;
import ru.akirakozov.sd.refactoring.servlet.AddProductServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AddProductServletTest {
    private AddProductServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ProductDAO productDAO;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        servlet = new AddProductServlet(productDAO);
    }

    @Test
    public void addProductTest() throws IOException {
        Mockito.when(request.getParameter("name"))
                .thenReturn("something");
        Mockito.when(request.getParameter("price"))
                .thenReturn("100500");
        StringWriter out = new StringWriter();
        PrintWriter printWriter = new PrintWriter(out);
        Mockito.when(response.getWriter())
                .thenReturn(printWriter);

        servlet.doGet(request, response);
        ArgumentCaptor<ProductDTO> productDTO = ArgumentCaptor.forClass(ProductDTO.class);
        Mockito.verify(productDAO, Mockito.times(1)).insert(productDTO.capture());

        printWriter.flush();
        Assert.assertEquals("something", productDTO.getValue().getName());
        Assert.assertEquals(100500, productDTO.getValue().getPrice());
        Assert.assertEquals("OK" + System.lineSeparator(), out.toString());
    }
}
