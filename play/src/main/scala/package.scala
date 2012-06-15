package jiranium

import anorm.Sql

package object play {

  implicit def richString(str: String) = new {

    // """select * from %s".formatSql("table_name")
    def formatSql(args: Any*) = Sql.sql(str.format(args: _*))
  }
}
