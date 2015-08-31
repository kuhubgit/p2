package controllers

import play.api._
import play.api.mvc._
import views.html._
import play.api.libs.json._
import com.fasterxml.jackson.databind._
import com.fasterxml.jackson.module.scala.DefaultScalaModule

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  // Hello World by return html (add views/hello.scala.html)
  def hello = Action {
    Ok(views.html.hello("api version 1."))
  }

  // Hello World by return json
  def rest = Action {
    val helo = Helo("helo")
    val mapper = new ObjectMapper
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    mapper.registerModule(DefaultScalaModule)
    Ok(mapper.writeValueAsString(helo))
  }

  case class Helo(val message: String)

}
