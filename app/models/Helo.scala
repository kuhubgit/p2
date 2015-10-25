package models

import play.api.libs.json.{Reads, JsPath}
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

case class Helo(message: String, version: String)


