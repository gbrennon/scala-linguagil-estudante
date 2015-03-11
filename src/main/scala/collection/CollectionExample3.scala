package collection

object CollectionExample3 extends App {
  def process(input: List[Article]): Map[String, Map[String, Int]] = ???

  val output = process(ArticleList.input)

  output.foreach(println)
  assert(output ==
    Map(
      "writing" -> Map("words" -> 1145, "articles" -> 1),
      "people" -> Map("words" -> 561, "articles" -> 1),
      "ddd" -> Map("words" -> 482, "articles" -> 1),
      "nosql" -> Map("words" -> 3906, "articles" -> 4),
      "orm" -> Map("words" -> 2279, "articles" -> 2),
      "ruby" -> Map("words" -> 1313, "articles" -> 1)
    )
  )
}
