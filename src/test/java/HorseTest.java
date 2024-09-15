import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {

    @Test
    public void constructor_NullNameParamPassed_ThrowsIllegalArgumentException() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 2, 3));

        assertEquals("Name cannot be null.", illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { " ", "  ", "   ", " \n ", " \t ", "\n", "\t", "\n \n", "\t \t", "\n\t", "\t\n", "\n\n", "\t\t" })
    public void constructor_EmptyNameParamPassed_ThrowsIllegalArgumentException(String name) {
        String waitingMessage = "Name cannot be blank.";

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 2, 3));

        assertEquals(waitingMessage, illegalArgumentException.getMessage());
    }

    @Test
    public void constructor_NegativdSpeedParamPassed_ThrowsIllegalArgumentException() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Bla-Bla", -1, 3));

        assertEquals("Speed cannot be negative.", illegalArgumentException.getMessage());
    }

    @Test
    public void constructor_NegativdDisnaceParamPassed_ThrowsIllegalArgumentException() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Bla-Bla", 1, -3));

        assertEquals("Distance cannot be negative.", illegalArgumentException.getMessage());
    }

    @Test
    void getName_ReturnCorrectValue() {
        Horse horse = new Horse("Jak", 1, 2);

        String nameReal = horse.getName();

        assertEquals("Jak", nameReal);
    }

    @Test
    void getSpeed_ReturnCorrectValue() {
        Horse horse = new Horse("Jak", 1, 2);

        double speedReal = horse.getSpeed();

        assertEquals(1, speedReal);
    }

    @Test
    void getDistance_ReturnCorrectValue() {
        Horse horse = new Horse("Jak", 1, 2);

        double distanceReal = horse.getDistance();

        assertEquals(2, distanceReal);
    }


    @Test
    void move_CallsGetRandomDoubleMethod() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Jak", 1, 2);

            horse.move();

            //порверка вызывался ли метод Horse.getRandomDouble
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8})
    public void move_Calculates(double fakeRanVal) {
        double min = 0.2;
        double max = 0.9;
        double speed = 2.5;
        double distance = 180;
        String name = "Jak";

        Horse horse = new Horse(name, speed, distance);

        double waitingDistanceValue = distance + speed * fakeRanVal;

        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(min, max)).thenReturn(fakeRanVal);

            horse.move();
        }

        assertEquals(waitingDistanceValue, horse.getDistance());
    }
}