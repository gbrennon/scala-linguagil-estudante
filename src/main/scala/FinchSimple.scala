import java.net.InetSocketAddress

import com.twitter.finagle.Service
import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.httpx.Http
import com.twitter.util.Future
import io.finch.response._
import io.finch.route._
import io.finch.{AnyOps, HttpRequest, HttpResponse}

object FinchSimple extends App {

  def hello(name: String) = new Service[HttpRequest, HttpResponse] {
    def apply(req: HttpRequest): Future[HttpResponse] = {
      Ok(s"Hello $name, welcome to SCALA + FINCH!").toFuture
    }
  }

  val endpoint: Endpoint[HttpRequest, HttpResponse] =
    Get / string("name") /> hello

  ServerBuilder()
    .codec(Http())
    .bindTo(new InetSocketAddress(8080))
    .name("todo-server")
    .build(endpoint)

}