package jiranium.anorm

import play.api.db._
import anorm._
import anorm.SqlParser._
import java.sql.{ Connection, Timestamp }
import org.joda.time.DateTime

object Query extends Query

trait Query extends scalaz.Options with scalaz.Booleans with scalaz.Identitys {

  import implicits._

  // Does not insert anything.
  // Instead, returns a Sql object with an execute()
  // method that performs the insert.
  def insert(tableName: String)(args: (Any, ParameterValue[_])*): SimpleSql[_] = {
    def toS(a: Any) = a match {
      case x: Symbol ⇒ x.name
      case x         ⇒ x.toString
    }
    def cast(typeOption: Option[String]) =
      typeOption.fold(t ⇒ t.startsWith("?").fold(t drop 1, t), "")
    val fields: Seq[(String, Option[String])] = args map {
      case ((k, t), _) ⇒ toS(k) -> toS(t).some
      case (k, _)      ⇒ toS(k) -> none
    }
    val params = args map {
      case ((k, _), v) ⇒ toS(k) -> v
      case (k, v)      ⇒ toS(k) -> v
    }
    "insert into %s (%s) values (%s)".formatSql(
      tableName,
      fields map (_._1) mkString ", ",
      fields map {
        case (name, typ) ⇒ "{" + name + "}" + cast(typ)
      } mkString ", "
    ).on(params: _*)
  }

  def timestamp(date: DateTime) = new Timestamp(date.getMillis)
}
