import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Categories;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import service.SampleService;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SampleServiceTest {
    SampleService service = new SampleService();
    Categories categories = new Categories();

    @Test
    public void unmarshall() throws JAXBException, FileNotFoundException {
        categories = service.unmarshall(this.getClass().getResource("categories.xml").getPath());
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

    @Test
    public void jsonToCategories() throws IOException {
        categories = service.jsonToCategories(this.getClass().getResource("categories.json").getPath());
        assertNotNull(categories);
    }

    @Test
    public void categoriesToJson() throws IOException {
        service.categoriesToJson(categories, this.getClass().getResource("generated/new-categories.json").getPath());
        JSONObject jsonSchema = new JSONObject(new JSONTokener(this.getClass().getResourceAsStream("/generated/categories-schema.json")));
        JSONObject jsonObject = new JSONObject(new JSONTokener(this.getClass().getResourceAsStream("/generated/new-categories.json")));
        org.everit.json.schema.Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonObject);
    }

    @Test
    public void staxReaderTestTrue() throws FileNotFoundException, XMLStreamException {
        assertTrue(service.staxReader(this.getClass().getResource("categories.xml").getPath()));
    }

    @Test
    public void staxReaderTestFalse() throws FileNotFoundException, XMLStreamException {
        assertFalse(service.staxReader(this.getClass().getResource("badXmlFile.xml").getPath()));
    }
}
