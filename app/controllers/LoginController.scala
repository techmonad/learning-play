package controllers

import javax.inject._

import models.UserRepositoryApi
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.I18nSupport
import play.api.mvc._
import utils.CacheManager

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class LoginController @Inject()(cacheManager: CacheManager, userRepository: UserRepositoryApi, cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  val loginForm = Form[(String, String)](
    tuple(
      "email" -> email,
      "password" -> nonEmptyText
    )
  )


  def login = Action { implicit request=>
    Ok(views.html.login("Play-scala-template"))
  }


  def authenticate = Action { implicit request =>

    println(" ===== " + request)
    loginForm.bindFromRequest.fold(
      _ => Redirect(routes.LoginController.login).withNewSession.flashing("ERROR" -> "Invalid email or password"),
      emailAndPassword => {
        println("===== " + emailAndPassword)
        val (email, password) = emailAndPassword
        userRepository.getUserByEmailAndPassword(email, password) match {
          case Some(user) =>
            println("fond user" + user)
            cacheManager.set(user.id.get.toString, user)
            Redirect(routes.HomeController.index()).withSession("user_id" -> user.id.get.toString)
          case None =>
            Redirect(routes.LoginController.login).withNewSession.flashing("ERROR" -> "Invalid email or password")
        }
      }

    )
  }

}
