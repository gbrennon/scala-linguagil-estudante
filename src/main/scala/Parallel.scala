object Parallel extends App {
  val list = (1 to 10000000).toList

  var t = System.currentTimeMillis
  list.map(_.toString).filter(_.matches("(0|2|4|6|8)$"))
  println(System.currentTimeMillis - t)

  t = System.currentTimeMillis
  list.par.map(_.toString).filter(_.matches("(0|2|4|6|8)$"))
  println(System.currentTimeMillis - t)
}
