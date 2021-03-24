import config.LocalDateAdapter;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalDateAdapterTest {
    LocalDateAdapter localDateAdapter = new LocalDateAdapter();

    @Test
    public void localDateAdapterMarshallTest(){
        LocalDate ld = LocalDate.of(2020, 12, 31);
        String string = localDateAdapter.marshal(ld);
        assertTrue(string != null && string.matches("\\d{2}-\\d{2}-\\d{4}"));
        assertEquals(string, "31-12-2020");
    }

    @Test
    public void localDateAdapterUnmarshallTest(){
        LocalDate ld = LocalDate.of(2020, 12, 31);
        assertEquals(ld, localDateAdapter.unmarshal("31-12-2020"));
    }
}
