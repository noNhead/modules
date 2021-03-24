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

    void categoriesToJson(Categories categories, String path) throws IOException;

    Categories jsonToCategories(String path) throws IOException;

    ObjectMapper objectMapperConfigure(ObjectMapper objectMapper);

    Boolean staxReader(String path) throws FileNotFoundException, XMLStreamException;
}
