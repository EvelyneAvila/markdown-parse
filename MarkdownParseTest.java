import static org.junit.Assert.*;
import org.junit.*;

//Imports in order to be able to copy getLinks from MarkdownParse.java to this file


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class MarkdownParseTest {

    //Copy and pasted getLinks method and containsNewLine to be able to do tests here

    /*
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
    }*/

    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    /*
    @Test
    public void testGetLinksF1() throws IOException {
        Path fileName = Path.of("testFile2.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        assertEquals("[]", links.toString());
    } */

    @Test
    public void testGetLinksF4() throws IOException {
        Path fileName = Path.of("test-file4.md");
	    String contents = Files.readString(fileName);
        //ArrayList<String> links = getLinks(contents);
        assertEquals("[https://something.com, some-page.html]", MarkdownParse.getLinks(contents));
    } 

    //Tests for Lab Report 4
    
    
    @Test
    public void testSnippet1() throws IOException {
        Path fileName = Path.of("snippet1.md");
	    String contents = Files.readString(fileName);
        assertEquals("[`google.com, google.com, ucsd.edu]", MarkdownParse.getLinks(contents));
    } 

    
    @Test
    public void testSnippet2() throws IOException {
        Path fileName = Path.of("snippet2.md");
	    String contents = Files.readString(fileName);
        assertEquals("[a.com, a.com(()), example.com]", MarkdownParse.getLinks(contents));
    }

    
    
    @Test
    public void testSnippet3() throws IOException {
        Path fileName = Path.of("snippet3.md");
	    String contents = Files.readString(fileName);
        assertEquals("[https://ucsd-cse15l-w22.github.io/]", MarkdownParse.getLinks(contents));
    }
}
