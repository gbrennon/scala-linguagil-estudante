package collection

object CollectionExample1 extends App {

  def process(input: List[Article]): Int = input.map(_.words).sum

  val output = process(ArticleList.input)
  println(s"Output: $output")

  assert(output == 5219)
}
