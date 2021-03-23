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
            categories = service.unmarshall(PluginCollect.class.getResource("based/categories.xml").getPath());
            service.marshall(categories, "pluginCollectors/src/generated/new-categories.xml");
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            categories = service.JsonToCategories(PluginCollect.class.getResource("based/categories.json").getPath());
            service.CategoriesToJson(categories, "pluginCollectors/src/generated/new-categories.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
