package jiranium.anorm

import play.api.db._
import anorm._
import anorm.SqlParser._
import java.sql.Connection

object Query extends Query

trait Query {

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
}
