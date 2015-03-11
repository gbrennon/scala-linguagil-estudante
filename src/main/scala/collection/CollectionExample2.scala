package collection

object CollectionExample2 extends App {
  def process(input: List[Article]): Map[String, Int] = input.groupBy(_.tpe).mapValues(_.size)

  val output = process(ArticleList.input)
  println(s"Output: $output")

  assert(output == Map("bliki" → 4, "article" → 1))
}
