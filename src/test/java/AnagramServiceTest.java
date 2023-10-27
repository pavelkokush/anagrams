import org.anagram.AnagramService;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AnagramServiceTest {

    @Test
    public void testIsAnagrams() {
        AnagramService anagramService = new AnagramService();

        assertTrue(anagramService.isAnagrams("abc", "abc"));
        assertTrue(anagramService.isAnagrams("abc", "bca"));
        assertTrue(anagramService.isAnagrams("abc", "a  b c"));
        assertTrue(anagramService.isAnagrams("abcc", "bcca"));
        assertFalse(anagramService.isAnagrams("abc", "abc c"));
        assertFalse(anagramService.isAnagrams("s", ""));
        assertFalse(anagramService.isAnagrams("", "t"));
    }

    @Test
    public void testGetAllKnownAnagrams() {
        //given
        AnagramService anagramService = new AnagramService();

        assertTrue(anagramService.isAnagrams("abc", "abc"));
        assertTrue(anagramService.isAnagrams("a b c", "bca"));
        assertFalse(anagramService.isAnagrams("abd", "abdd"));

        //when-then
        assertEquals(anagramService.getAllKnownAnagrams("abc"), Set.of("a b c", "bca"));
        assertEquals(anagramService.getAllKnownAnagrams("a b c"), Set.of("abc", "bca"));
        assertEquals(anagramService.getAllKnownAnagrams("bca"), Set.of("abc", "a b c"));
        assertTrue(anagramService.getAllKnownAnagrams("abd").isEmpty());
        assertTrue(anagramService.getAllKnownAnagrams("").isEmpty());
    }
}
