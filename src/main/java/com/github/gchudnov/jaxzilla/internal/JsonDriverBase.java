package com.github.gchudnov.jaxzilla.internal;

import com.github.gchudnov.jaxzilla.JsonHandler;

class JsonDriverBase {

  JsonHandler handler = new DefaultJsonHandler();

  public void setHandler(JsonHandler handler) {
    this.handler = handler;
  }

  protected void startDocument() {
    handler.onStartDocument();
  }

  protected void endDocument() {
    handler.onEndDocument();
  }

  protected void startObject() {
    handler.onStartObject();
  }

  protected void startKeyValue() {
    handler.onStartKeyValue();
  }

  protected void key(String image) {
    handler.onKey(image);
  }

  protected void endKeyValue() {
    handler.onEndKeyValue();
  }

  protected void endObject() {
    handler.onEndObject();
  }

  protected void startArray() {
    handler.onStartArray();
  }

  protected void startElement() {
    handler.onStartElement();
  }

  protected void endElement() {
    handler.onEndElement();
  }

  protected void endArray() {
    handler.onEndArray();
  }

  protected void valueString(String image) {
    String x = image.substring(1, image.length() - 1); // remove quotes
    handler.onStringValue(x);
  }

  protected void valueInteger(String image) {
    long x = Long.parseLong(image);
    handler.onIntegerValue(x);
  }

  protected void valueDecimal(String image) {
    double x = Double.parseDouble(image);
    handler.onDecimalValue(x);
  }

  protected void valueBoolean(boolean b) {
    handler.onBooleanValue(b);
  }

  protected void valueNull() {
    handler.onNullValue();
  }

}
