package org.icann.rdapconformance.validator;

import java.io.IOException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SchemaValidatorErrorResponseDescriptionTest extends SchemaValidatorForArrayOfStringTest {

  public SchemaValidatorErrorResponseDescriptionTest() {
    super(
        "rdap_error_object.json",
        "/validators/error/valid.json");
  }

  @BeforeMethod
  @Override
  public void setUp() throws IOException {
    super.setUp();
    name = "description";
  }

  /**
   * 7.2.17.6
   */
  @Test
  public void invalid() {
    invalid(-12105);
  }

  /**
   * 7.2.17.7
   */
  @Test
  public void notListOfString() {
    notListOfString(-12106);
  }
}
