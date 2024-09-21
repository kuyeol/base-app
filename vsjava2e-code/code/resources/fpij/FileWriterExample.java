/***
 * Excerpted from "Functional Programming in Java, Second Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit https://pragprog.com/titles/vsjava2e for more book information.
***/
package fpij;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {
  private final FileWriter writer;
  
  public FileWriterExample(final String fileName) throws IOException {
    writer = new FileWriter(fileName);
  }
  public void writeStuff(final String message) throws IOException {
    writer.write(message);
  }
  public void finalize() throws IOException { //Deprecated in Java 9
    writer.close();
  }
  //...

  public void close() throws IOException { //Not a good solution
    writer.close();
  }

/*
  public static void main(final String[] args) throws IOException {
    final FileWriterExample writerExample = 
      new FileWriterExample("peekaboo.txt");

    writerExample.writeStuff("peek-a-boo");      
  }
*/

public static void callClose(final String[] args) throws IOException {
    final FileWriterExample writerExample = 
      new FileWriterExample("peekaboo.txt");
      
    writerExample.writeStuff("peek-a-boo");      
    writerExample.close();
}

  public static void main(final String[] args) throws IOException {
    final FileWriterExample writerExample = 
      new FileWriterExample("peekaboo.txt");
    
    try { //Rather verbose
      writerExample.writeStuff("peek-a-boo");            
    } finally {
      writerExample.close();      
    }
  }

}
