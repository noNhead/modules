package service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import entity.Categories;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;

public class SampleService implements Service {
    @Override
    public void marshall(Categories categories, String path) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Categories.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(categories, new File(path));
    }

    @Override
    public Categories unmarshall(String path) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(Categories.class);
        return (Categories) context.createUnmarshaller().unmarshal(new FileReader(path));
    }

    @Override
    public void categoriesToJson(Categories categories, String path) throws IOException {
        ObjectMapper objectMapper = objectMapperConfigure(new ObjectMapper());
        objectMapper.writeValue(new File(path), categories);
    }

    @Override
    public Categories jsonToCategories(String path) throws IOException {
        ObjectMapper objectMapper = objectMapperConfigure(new ObjectMapper());
        return objectMapper.readValue(new File(path), Categories.class);
    }

    @Override
    public ObjectMapper objectMapperConfigure(ObjectMapper objectMapper){
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Override
    public Boolean staxReader(String path) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(path));

        while (reader.hasNext()){
            XMLEvent nextEvent = reader.nextEvent();
            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                if ("category".equals(startElement.getName().getLocalPart())) {
                    return true;
                }
            }
        }
        return false;
    }
}
