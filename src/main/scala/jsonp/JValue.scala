package jsonp

sealed trait JValue {
  override def toString: String = this match {
    case JNull => "null"
    case JBool(value) => value.toString
    case JString(value) => "\"" + escape(value) + "\""
    case JNumber(int, frac, exponent) => {
      val fracStr = if (frac.isEmpty) "" else "." + frac.mkString("")
      val expStr = if (exponent == BigInt(0)) "" else "e" + exponent.toString
      int.toString + fracStr + expStr
    }
    case JArray(values) => values.map(_.toString).mkString("[", ",", "]")
    case JObject(entries) => entries.map { case (k, v) => "\"" + escape(k) + "\":" + v.toString }.mkString("{", ",", "}")
  }

  private def escape(str: String): String = {
    str.flatMap {
      case '"' => "\\\""
      case '\\' => "\\\\"
      case '\n' => "\\n"
      case '\r' => "\\r"
      case '\t' => "\\t"
      case c if c.isControl => "\\u%04x".format(c.toInt)
      case c => c.toString
    }
  }
}

case object JNull extends JValue
case class JBool(value: Boolean) extends JValue
case class JString(value: String) extends JValue
case class JNumber(int: BigInt, frac: List[Int], exponent: BigInt) extends JValue
case class JArray(values: List[JValue]) extends JValue
case class JObject(entries: List[(String, JValue)]) extends JValue
