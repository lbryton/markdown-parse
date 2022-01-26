import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.*;

//javac -cp .;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar MarkdownParseTest.java 
//javac -cp "lib/\*" MarkdownParseTest.java

//java -cp .;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar org.junit.runner.JUnitCore MarkdownParseTest
public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }
    @Test
    public void testFile1() throws IOException{
        Path fileName = Path.of(".\\test-file.md");
        String contents = Files.readString(fileName);
        List<String> expected = List.of("https://something.com", "some-page.html");
        assertEquals(expected, MarkdownParse.getLinks(contents));

    }
    @Test
    public void testFile2() throws IOException{
        Path fileName = Path.of(".\\test-file2.md");
        String contents = Files.readString(fileName);
        List<String> expected = List.of();
        assertEquals(expected, MarkdownParse.getLinks(contents));

    }
}