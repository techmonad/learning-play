package controllers

import javax.inject.Inject

import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.routing.JavaScriptReverseRouter
import controllers.routes.javascript._

class JavaScriptRouteController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {


  val routes = List(
    LoginController.authenticate
  )

  def jsRoutes() = Action { implicit request =>
    Ok(JavaScriptReverseRouter("jsRoutes")(routes: _*))

  }

}
