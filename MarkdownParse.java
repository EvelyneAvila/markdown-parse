// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )

        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if(nextOpenBracket < 0){
                currentIndex += 1;
                continue;
            }
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            if(nextCloseBracket < 0){
                //make sure we are after the next index of the non-found string
                currentIndex += nextOpenBracket + 1;
                continue;
            }
            int openParen = markdown.indexOf("(", nextCloseBracket);
            if(openParen < 0){
                currentIndex += nextCloseBracket + 1;
                continue;
            }
            int closeParen = markdown.indexOf(")", openParen);
            if(closeParen < 0){
                currentIndex += openParen + 1;
                continue;
            }
            if(containsNewLine(markdown.substring(openParen + 1, closeParen))){
                // New variable for finding the index of new lines
                StringBuffer stringBuffer = new StringBuffer(markdown.substring(openParen + 1, closeParen));
                currentIndex = stringBuffer.indexOf("\n") + 1;
                // System.out.println("New line at " + stringBuffer.indexOf("\n"));
            } else {
                toReturn.add(markdown.substring(openParen + 1, closeParen));
                currentIndex = closeParen + 1;
            }
        }
        return toReturn;
    }

    static boolean containsNewLine(String str) {
        Pattern regex = Pattern.compile("^(.*)$", Pattern.MULTILINE);
            return regex.split(str).length > 0;
    }


    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}