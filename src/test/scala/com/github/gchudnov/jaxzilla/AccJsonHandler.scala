package com.github.gchudnov.jaxzilla

class AccJsonHandler extends JsonHandler {
  private val buf = new StringBuilder()

  def history: String =
    buf.toString()

  override def onStartDocument(): Unit =
    buf.append("onStartDocument\n")

  override def onEndDocument(): Unit =
    buf.append(s"onEndDocument\n")

  override def onStartObject(): Unit =
    buf.append(s"onStartObject\n")

  override def onKey(key: String): Unit =
    buf.append(s"onKey: ${key}\n")

  override def onStartKeyValue(): Unit =
    buf.append(s"onStartKeyValue\n")

  override def onEndKeyValue(): Unit =
    buf.append(s"onEndKeyValue\n")

  override def onEndObject(): Unit =
    buf.append(s"onEndObject\n")

  override def onStartArray(): Unit =
    buf.append(s"onStartArray\n")

  override def onStartElement(): Unit =
    buf.append(s"onStartElement\n")

  override def onEndElement(): Unit =
    buf.append(s"onEndElement\n")

  override def onEndArray(): Unit =
    buf.append(s"onEndArray\n")

  override def onStringValue(value: String): Unit =
    buf.append(s"onStringValue: ${value}\n")

  override def onIntegerValue(value: Long): Unit =
    buf.append(s"onIntegerValue: ${value}\n")

  override def onDecimalValue(value: Double): Unit =
    buf.append(s"onDecimalValue: ${value}\n")

  override def onBooleanValue(value: Boolean): Unit =
    buf.append(s"onBooleanValue: ${value}\n")

  override def onNullValue(): Unit =
    buf.append(s"onNullValue\n")
}
