package models

import javax.inject.Singleton

import com.google.inject.ImplementedBy

@ImplementedBy(classOf[FakeUserRepository])
trait UserRepositoryApi {

  def getUserByEmailAndPassword(email: String, password: String): Option[User]

}


@Singleton
class FakeUserRepository extends UserRepositoryApi {
  override def getUserByEmailAndPassword(email: String, password: String) =
    if (email == "satendra@gmail.com")
      Option(User("Satendra", "satendra@gmail.com", "pass", Some(1)))
    else
      None
}

case class User(name: String, email: String, password: String, id: Option[Int] = None)