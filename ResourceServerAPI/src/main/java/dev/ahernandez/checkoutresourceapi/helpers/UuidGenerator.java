package dev.ahernandez.checkoutresourceapi.helpers;

import java.util.UUID;

/**
 * Provides a method to generate a random UUID.
 */
public class UuidGenerator {

  private UuidGenerator() {}

  /**
   * Generates UUID.
   *
   * @return String
   */
  public static String generate() {
    return UUID.randomUUID().toString();
  }
}
