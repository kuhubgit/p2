package conf

import play.api._
import play.api.mvc.Results._
import play.api.mvc._

import scala.concurrent.Future

/**
 *
 * @author forrestinc
 */
object Production extends GlobalSettings {

    override def onHandlerNotFound(request: RequestHeader) = {
        Future.successful(NotFound(
            views.html.error.s404()))
    }
}
