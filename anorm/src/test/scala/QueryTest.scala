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
      "typed with a question mark" in {
        insert('a -> "?::uuid" -> "b").sql.query must_== "insert into t (a) values (?::uuid)"
      }
    }
    "query args" in {
      "simple" in {
        insert('a -> "b").params.headOption must beSome.like {
          case ("a", param) ⇒ param.aValue.toString must_== "b"
        }
      }
      "multi value" in {
        insert('a -> "b", 'c -> "d").params.lastOption must beSome.like {
          case ("c", param) ⇒ param.aValue.toString must_== "d"
        }
      }
      "typed" in {
        insert('a -> "::uuid" -> "b").params.headOption must beSome.like {
          case ("a", param) ⇒ param.aValue.toString must_== "b"
        }
      }
      "typed with question mark" in {
        insert('a -> "?::uuid" -> "b").params.headOption must beSome.like {
          case ("a", param) ⇒ param.aValue.toString must_== "b"
        }
      }
    }
  }
}
