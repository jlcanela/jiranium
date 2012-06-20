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
    val fields = args map {
      case (s: Symbol, _) ⇒ s.name
      case (k, _)         ⇒ k.toString
    }
    "insert into %s (%s) values (%s)".formatSql(
      tableName,
      fields mkString ", ",
      fields map ("{"+_+"}") mkString ", "
    ).on(args: _*)
  }

  def timestamp(date: DateTime) = new Timestamp(date.getMillis)
}
