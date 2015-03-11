package rest

import java.net.InetSocketAddress

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.twitter.finagle.Service
import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.httpx.Http
import com.twitter.util.Future
import io.finch.jackson._
import io.finch.request._
import io.finch.response._
import io.finch.route._
import io.finch.{AnyOps, HttpRequest, HttpResponse}

import scala.collection.mutable

object Server {
  def main(args: Array[String]) {
    ServerBuilder()
      .codec(Http())
      .bindTo(new InetSocketAddress(8080))
      .name("todo-server")
      .build(Todo.endpoint)
  }
}

object Todo {
  import rest.Store._

  implicit val objectMapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)

  val endpoint: Endpoint[HttpRequest, HttpResponse] =
    (Get / "todo" / int("id") />  getTodo) |
      (???) | // Defina a rota para listar
      (Post / "todo" /> createTodo) |
      (???) | // Defina a rota de Delete
      (???) // Defina a rota de Editar

  val todoReader: RequestReader[Todo] = RequiredBody.as[Todo]

  def getTodo(id: Int) = new Service[HttpRequest, HttpResponse] {
    def apply(req: HttpRequest): Future[HttpResponse] = {
      todos.get(id) match {
        case Some(todo) => Ok(todo).toFuture
        case None => NotFound().toFuture
      }
    }
  }

  def listTodo() = ???

  def createTodo() = new Service[HttpRequest, HttpResponse] {
    def apply(req: HttpRequest): Future[HttpResponse] = {
      for (todo <- todoReader(req)) yield {
        val newId = nextId()
        val newTodo = todo.copy(id = newId)

        todos.put(newId, newTodo)

        Ok(newTodo)
      }
    }
  }

  def deleteTodo(id: Int) = ???

  def editTodo(id: Int) = ???

}

object Store {
  case class Todo(id: Int, title: String, description: String, finished: Boolean = false)

  val todos: mutable.Map[Int, Todo] = ???

  var currentId: Int = ???
  def nextId(): Int = ???
}
