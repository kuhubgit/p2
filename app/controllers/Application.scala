package controllers

import com.fasterxml.jackson.databind._
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import models.Helo
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json.{JsPath, Json, Reads}
import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  /* return html (add views/hello.scala.html) */
  def hello = Action {
    Ok(views.html.hello("api version 1."))
  }

  /* return json */
  def rest = Action {
    val helo = Helo("helo", "1")
    val mapper = new ObjectMapper
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    mapper.registerModule(DefaultScalaModule)
    Ok(mapper.writeValueAsString(helo))
  }

  /* validate post json */
  implicit val heloFormatter: Reads[Helo] = (
    (JsPath \ "message").read[String] and
      (JsPath \ "version").read[String]
    )(Helo.apply _)

  def post = Action(parse.json) {
    request =>
      val helo = request.body.validate[Helo]
      helo.fold(
        errors => {
          BadRequest(Json.obj("message" -> "Json Syntax Error."))
        },
        helo => {
          Ok(Json.obj("message" -> "Json Syntax Ok."))
        }
      )
  }

}
