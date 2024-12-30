import jsonp.{JBool, JNumber, JObject}

@main
def main(): Unit =
  val obj = JObject(List(("foo", JNumber(42, List(), BigInt(0))), ("bar", JBool(true))))
  println(obj)
