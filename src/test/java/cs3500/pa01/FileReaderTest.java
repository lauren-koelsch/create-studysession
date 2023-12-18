package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class FileReaderTest {

  @Test
  public void testReadFile() throws IOException {

    FileReader reader = new FileReader();
    ArrayList<String> content = reader.readFile("examples/test.md");

    assertEquals(content.get(0), "Hello");
  }
}