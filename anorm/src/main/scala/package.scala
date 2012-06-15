package jiranium

import _root_.anorm.Sql

package object anorm {

  implicit def richString(str: String) = new {

    // """select * from %s".formatSql("table_name")
    def formatSql(args: Any*) = Sql.sql(str.format(args: _*))
  }
}
