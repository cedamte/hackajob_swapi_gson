import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SolutionTest {

    private final String character;
    private final int numberOfFilms;

    public SolutionTest(String character, int numberOfFilms) {
        this.character = character;
        this.numberOfFilms = numberOfFilms;
    }

    @Parameterized.Parameters
    public static Collection<Object> data() {
        return Arrays.asList(new Object[][]{
                {"Luke Skywalker", 5},
                {"C-3PO", 6},
                {"R2-D2", 7},
                {"Darth Vader", 4},
                {"Leia Organa", 5},
        });
    }

    @Test
    public void testFilmThisPersonHasBeenIn() {
        int actual = Solution.run(character);
        assertEquals(numberOfFilms, actual);
    }
}