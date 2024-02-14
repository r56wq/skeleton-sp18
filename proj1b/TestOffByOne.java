import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void Test0ffByOne() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertFalse(offByOne.equalChars('a', 's'));
    }

    @Test
    public void TestPalindrome() {
        Palindrome testP = new Palindrome();
        assertTrue(testP.isPalindrome("hi", offByOne));
        assertFalse(testP.isPalindrome("adc", offByOne));
        assertTrue(testP.isPalindrome("flake", offByOne));
    }

}
