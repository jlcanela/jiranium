Jiranium
========

A set of reusable components we use in our scala programs.

Installation
------------

Add this to your SBT config:

```scala
// resolver
val jirafeSnaps = "jirafe.com" at "https://raw.github.com/jirafe/mvn-repo/master/releases"

// dependency
val jiraniumAnorm = "com.jirafe" %% "jiranium-anorm" % "1.0"
```

Play2 utilities
===============

Anorm
-----

### Sql parsers

```scala
import jiranium.anorm.SqlParsers._

val mapping =
  optInt("my_table.plan_id") ~ // parses an Option[Int]
  dateTime("my_table.created_t_stamp") // parses a joda.org.time.DateTime
```
See more parsers at play/src/main/scala/SqlParser.scala

### Query utilities

```scala
import jiranium.anorm.Query

// insert in my_table
Query.insert("my_table")('foo -> "omg", 'bar -> "ponies!!").execute()

// is equivalent to:
SQL("""insert into %s (foo, bar) values ({foo}, {bar})""" format "my_table")
.on('foo -> "omg", 'bar -> "ponies!!")
.execute()
```

How to publish to jirafe/mvn-repo
---------------------------------

This section is intended to Jirafe developers.
First of all, increment the version number in project/Build.scala.
Then publish and push to github mvn-repo:

    sbt publish
    cd /path/to/mvn-repo
    cp -r ~/.m2/repository/releases/com/jirafe/jiranium-anorm_2.9.1/* releases/com/jirafe/jiranium-anorm_2.9.1/
    git add releases/com/jirafe/jiranium-anorm_2.9.1/
    git commit -m "publish jiranium"
    git push
    cd -
