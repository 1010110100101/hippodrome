import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    //проверяет, ЧТО МЕТОД ВЫПОЛНЯЕТСЯ НЕ БОЛЕЕ ЧЕМ ЗА 22 секунды
    @Test
    @Disabled
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    void main() throws Exception {
        Main.main(new String[]{});
    }
}