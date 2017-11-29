package controllers

import javax.inject.{Inject, Singleton}

import models.User
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}
import utils.CacheManager

@Singleton
class HomeController @Inject()(cacheManager: CacheManager, cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  def index = Action { implicit request =>
    request.session.get("user_id") match {
      case Some(userId) =>
        cacheManager.get[User](userId)
          .fold(Redirect(routes.LoginController.login()))(user => Ok(views.html.index(user)))
      case None =>
        Redirect(routes.LoginController.login())
    }
  }

}
