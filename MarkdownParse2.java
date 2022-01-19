// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse2 {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            System.out.println("Markdown length: " + markdown.length());
            System.out.println(currentIndex);
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            System.out.println("nextOpenBracket: " + nextOpenBracket);
            if (nextOpenBracket == -1) break;
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            System.out.println("nextCloseBracket: " + nextCloseBracket);
            if (nextCloseBracket == -1) break;
            int openParen = markdown.indexOf("(", nextCloseBracket);
            System.out.println("openParen: " + openParen);
            if (openParen == -1) break;
            if (openParen == nextCloseBracket + 1){
                int closeParen = markdown.indexOf(")", openParen);
                System.out.println("closeParen: " + closeParen);
                if (closeParen == -1) break;
                toReturn.add(markdown.substring(openParen + 1, closeParen));
                currentIndex = closeParen + 1;
            }
            else currentIndex = openParen;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}