import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HorseTest {

    private final String name = "Horse";
    private final int speed = 1;
    private final int distance = 10;

    @Test
    void whenNullName() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null,100)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {""," ","\t"})
    void whenEmptyName(String name) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Horse(name,100)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void whenNegativeSpeed(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,() -> new Horse(name,-1)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void whenNegativeDistance(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,() -> new Horse("Horse",100,-1)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameTest(){
        Horse horse = new Horse(name,speed);
        assertEquals(name,horse.getName());
    }

    @Test
    void getSpeedTest(){
        Horse horse = new Horse(name,speed);
        assertEquals(speed,horse.getSpeed());
    }

    @Test
    void getDistanceTest(){
        Horse horse = new Horse(name,speed, distance);
        assertEquals(distance,horse.getDistance());
    }

    @Test
    void whenHorseMoveGetOneNumbRandomDouble(){
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
            Horse horse = new Horse(name,speed, distance);
            horse.move();
            horseMockedStatic.verify(() ->Horse.getRandomDouble(0.2, 0.9), Mockito.times(1));
        }
    }
}
