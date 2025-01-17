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
            int nextOpenBracket = loc(markdown, currentIndex, "[");
            //markdown.indexOf("[", currentIndex);
            int nextCloseBracket = loc(markdown, currentIndex, "]");
            //markdown.indexOf("]", nextOpenBracket);
            int openParen = loc(markdown, currentIndex, "(");
            //markdown.indexOf("(", nextCloseBracket);
            int closeParen = loc(markdown, currentIndex, ")");
            //markdown.indexOf(")", openParen);

            if (nextOpenBracket == -1 || nextCloseBracket == -1 
                || openParen == -1 || closeParen == -1){
                break;
                
            }
            else if (openParen != nextCloseBracket + 1){
                currentIndex = openParen + 1;
            }
            else if (markdown.indexOf("!") == nextOpenBracket - 1 
                && nextOpenBracket != 0){
                currentIndex = nextCloseBracket + 1;
            }
            else {
                toReturn.add(markdown.substring(openParen + 1, closeParen));
                currentIndex = closeParen + 1;
            }
        }
        return toReturn;
    }
    public static int loc (String str, int curr, String search){
        int pos = curr;
        int index = 0;
        while (index != -1){
            index = str.indexOf(search, pos);
            if (str.indexOf("\\") != index - 1){
                return index;
            }
            pos= index + 1;

        }
        return index;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}