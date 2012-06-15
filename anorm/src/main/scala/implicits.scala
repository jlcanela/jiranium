package jiranium.anorm

import anorm.Sql

object implicits extends implicits

trait implicits {

  implicit def stringWithFormatSql(str: String) = new {

    // """select * from %s".formatSql("table_name")
    def formatSql(args: Any*) = Sql.sql(str.format(args: _*))
  }
}
