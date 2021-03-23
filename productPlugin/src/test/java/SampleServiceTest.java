import entity.Categories;
import org.junit.Test;
import service.SampleService;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SampleServiceTest {
    SampleService service = new SampleService();
    Categories categories = new Categories();

    @Test
    public void unmarshall() throws JAXBException, FileNotFoundException {
        categories = service.unmarshall(SampleServiceTest.class.getResource("src/test/test-resource/categories.xml").getPath());
        assertNotNull(categories);
    }

    @Test
    public void marshall() throws JAXBException {
        service.marshall(categories, "productPlugin/src/test-resource/generated/new-categories.xml");
    }
}
