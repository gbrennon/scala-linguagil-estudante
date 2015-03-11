package collection

case class Article(title: String, words: Int, tags: List[String], tpe: String)

object ArticleList {
  val input = List(
    Article("NoDBA", 561, List("nosql", "people", "orm"), "bliki"),
    Article("Infodeck", 1145, List("nosql", "writing"), "bliki"),
    Article("OrmHat", 1718, List("nosql", "orm"), "bliki"),
    Article("ruby", 1313, List("ruby"), "article"),
    Article("DDD_Aggregate", 482, List("nosql", "ddd"), "bliki")
  )
}
