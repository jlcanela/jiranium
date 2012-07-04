package jiranium.anorm

import org.specs2.mutable.Specification

object QueryTest extends Specification {

  val insert = Query.insert("t") _ 

  "Insert" should {
    "query string" in {
      "simple" in {
        insert('a -> "b").sql.query must_== "insert into t (a) values (?)"
      }
      "multi value" in {
        insert('a -> "b", 'c -> "d").sql.query must_== "insert into t (a, c) values (?, ?)"
      }
      "typed" in {
        insert('a -> "::uuid" -> "b").sql.query must_== "insert into t (a) values (?::uuid)"
      }
      "interval" in {
        insert('a -> "* '1s'::interval" -> "b").sql.query must_== "insert into t (a) values (?* '1s'::interval)"
      }
    }
  }
}
