import entity.Categories;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import service.SampleService;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

public class SampleServiceTest {
    SampleService service = new SampleService();
    Categories categories = new Categories();

    @Test
    public void unmarshall() throws JAXBException, FileNotFoundException {
        categories = service.unmarshall(SampleServiceTest.class.getResource("src/test/test-resource/categories.xml").getPath());
        assertNotNull(categories);
    }

    @Test
    public void marshall() throws JAXBException, SAXException {
        service.marshall(categories, this.getClass().getResource("generated/new-categories.xml").getPath());
        Source xmlFile = new StreamSource(new File(this.getClass().getResource("generated/new-categories.xml").getPath()));
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(this.getClass().getResource("generated/categories.xsd"));
        Validator validator = schema.newValidator();
        assertThrows(SAXParseException.class, () -> validator.validate(xmlFile));
    }
}
