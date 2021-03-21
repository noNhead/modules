package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Categories;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Service {

    void marshall(Categories categories, String path) throws JAXBException;

    Categories unmarshall(String path) throws JAXBException, FileNotFoundException;

    void CategoriesToJson(Categories categories, String path) throws IOException;

    Categories JsonToCategories(String path) throws IOException;

    ObjectMapper objectMapperConfigure(ObjectMapper objectMapper);

    Categories staxReader() throws FileNotFoundException, XMLStreamException;
}
