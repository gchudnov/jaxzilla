package com.github.gchudnov.jaxzilla;

public interface JsonHandler {
  void onStartDocument();
  void onEndDocument();

  void onStartObject();
  void onStartKeyValue();
  void onKey(String key);
  void onEndKeyValue();
  void onEndObject();

  void onStartArray();
  void onStartElement();
  void onEndElement();
  void onEndArray();

  void onStringValue(String value);
  void onIntegerValue(long value);
  void onDecimalValue(double value);
  void onBooleanValue(boolean value);
  void onNullValue();
}
