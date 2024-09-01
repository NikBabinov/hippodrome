import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
public class HippodromeTest {

    @Test
    void nullTestCreateHippodrome() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );

        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void emptyTestCreateHippodrome() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<Horse>())
        );

        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("ewF", i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> horses1 = hippodrome.getHorses();
        for (int i = 0; i < horses1.size(); i++) {
            assertEquals(horses.get(i), horses1.get(i));
        }
    }

    @Test
    void moveTest(){
        Horse horse = Mockito.mock(Horse.class);
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(horse);
        }
        new Hippodrome(horses).move();
        Mockito.verify(horse,Mockito.times(horses.size())).move();
    }

    @Test
    void getWinnerTest(){
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(new Horse("ewF", i, i));
        }
        Horse horseMaxDistance = horses.stream().max(Comparator.comparing(Horse::getDistance)).get();
        assertEquals(horseMaxDistance,new Hippodrome(horses).getWinner());
    }
}
