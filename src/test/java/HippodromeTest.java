import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    public void constructor_NullListParamPassed_ThrowsIllegalArgumentException() {

        List<Horse> horses = null;

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses));

        assertEquals("Horses cannot be null.", illegalArgumentException.getMessage());
    }

    @Test
    public void constructor_EmptyListParamPassed_ThrowsIllegalArgumentException() {

        List<Horse> horses = new ArrayList<>();

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses));

        assertEquals("Horses cannot be empty.", illegalArgumentException.getMessage());
    }


    @Test
    void getHorses_ReturnListWithAllHorses() {
        List<Horse> horses = new ArrayList<>();

        for(int i = 0; i < 10; i++)
            horses.add(new Horse("HorseName_"+i, i, i));

        Hippodrome hippodrome = new Hippodrome(horses);

        assertNotNull(hippodrome.getHorses());
        assertEquals(10, hippodrome.getHorses().size());
        assertEquals("HorseName_0", hippodrome.getHorses().get(0).getName());
        assertEquals("HorseName_3", hippodrome.getHorses().get(3).getName());
        assertEquals("HorseName_8", hippodrome.getHorses().get(8).getName());
        assertEquals("HorseName_9", hippodrome.getHorses().get(9).getName());
    }

    @Test
    void move_CallsMooveMethodForAllHorses() {

        List<Horse> horses = new ArrayList<>();

        for(int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for(Horse horse : horses)
            Mockito.verify(horse, Mockito.times(1)).move(); // проверка, что для объекта horse был вызван один раз метод move()
    }

    @Test
    void getWinner_ReturnRealWinnerHorse() {

        Hippodrome hippodrome = new Hippodrome(List.of(
                new Horse("name1", 1, 10),
                new Horse("name2", 1, 50),
                new Horse("name3", 1, 40)
        ));

        assertEquals("name2", hippodrome.getWinner().getName());
    }
}