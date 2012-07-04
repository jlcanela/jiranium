package jiranium.anorm

import play.api.db._
import anorm._
import anorm.SqlParser._
import java.sql.{ Connection, Timestamp }
import org.joda.time.DateTime

object Query extends Query

trait Query {

  import implicits._

  // Does not insert anything.
  // Instead, returns a Sql object with an execute()
  // method that performs the insert.
  def insert(tableName: String)(args: (Any, ParameterValue[_])*): SimpleSql[_] = {
    def toS(a: Any) = a match {
      case x: Symbol ⇒ x.name
      case x         ⇒ x.toString
    }
    val fields: Seq[(String, Option[String])] = args map {
      case ((k, t), _) ⇒ toS(k) -> Some(toS(t))
      case (k, _)      ⇒ toS(k) -> None
    }
    "insert into %s (%s) values (%s)".formatSql(
      tableName,
      fields map (_._1) mkString ", ",
      fields map {
        case (name, typ) ⇒ "{" + name + "}" + (typ getOrElse "")
      } mkString ", "
    ).on(args: _*)
  }

  def timestamp(date: DateTime) = new Timestamp(date.getMillis)
}
