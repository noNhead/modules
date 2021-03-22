import entity.Categories;
import service.SampleService;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PluginCollect {

    public static void main(String[] args) {
        Categories categories;
        SampleService service = new SampleService();

        try {
            categories = service.unmarshall("based/categories.xml");
            service.marshall(categories, "");
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            categories = service.JsonToCategories("");
            service.CategoriesToJson(categories, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
