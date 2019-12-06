package com.Terminal;

/**
 * Form Field Info
 */
public class Field {
  /**
   * Label of the field
   */
  final String label;
  /**
   * Maximum length if used
   */
  final int maxLength;
  /**
   * Type of field
   */
  final FieldType fieldType;

  /**
   * Constructor
   * @param label label of the field
   * @param maxLength maximum length if used
   * @param fieldType type of field
   */
  Field(String label, int maxLength, FieldType fieldType) {
    this.label = String.valueOf(label);
    this.maxLength = maxLength;
    this.fieldType = fieldType;
  }

  /**
   * toString return
   * @return label for displaying
   */
  @Override
  public String toString() {
    return label;
  }
}
