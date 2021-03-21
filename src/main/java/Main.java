import prop.PropertiesReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        PropertiesReader reader = null;
        try {
             reader = new PropertiesReader("config.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String appConfigPath = null;
        if (reader != null) {
            appConfigPath = reader.getProperty("resourcesPath");
        }

        System.out.println(appConfigPath);

/*
        Properties properties = new Properties();
        String pathToXmlBased = properties.getProperty("pathToXmlBased");
        try {
            categories = service.unmarshall(pathToXmlBased);
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            service.marshall(categories, pathToXmlBased);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        try {
            service.JsonToCategories(pathToXmlBased);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            service.CategoriesToJson(categories, pathToXmlBased);
        } catch (IOException e) {
            e.printStackTrace();
        }

 */
    }
}
