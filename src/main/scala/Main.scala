import jsonp.Const

@main
def hello(): Unit =
  println(Const.greeting)
  println(msg)

def msg = "I was compiled by Scala 3. :)"
