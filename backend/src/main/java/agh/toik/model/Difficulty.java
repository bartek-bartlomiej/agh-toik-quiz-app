package agh.toik.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets Difficulty
 */
public enum Difficulty {
  HARD("hard"),
    MEDIUM("medium"),
    EASY("easy");

  private String value;

  Difficulty(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Difficulty fromValue(String text) {
    for (Difficulty b : Difficulty.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
