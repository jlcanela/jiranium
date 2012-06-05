Jiranium
========

A set of reusable components we use in our scala programs.

Installation
------------

Add this to your SBT config:

```scala
// resolver
val jirafeSnaps = "jirafe.com snaps" at "https://raw.github.com/jirafe/mvn-repo/master/snapshots"

// dependency
val jiraniumPlay = "com.jirafe" %% "jiranium-play" % "1.2-SNAPSHOT"
```

Play2 utilities
===============

Anorm
-----

### Sql parsers

```scala
import jiranium.play.SqlParsers._

val mapping =
  optInt("my_table.plan_id") ~ // parses an Option[Int]
  dateTime("my_table.created_t_stamp") // parses a joda.org.time.DateTime
```
See more parsers at play/src/main/scala/SqlParser.scala

### Query utilities

```scala
import jiranium.play.Query

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
    cp -r ~/.m2/repository/snapshots/com/jirafe/jiranium-play_2.9.1/* snapshots/com/jirafe/jiranium-play_2.9.1/
    git add snapshots/com/jirafe/jiranium-play_2.9.1/
    git commit -m "publish jiranium"
    git push
    cd -
