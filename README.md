jiranium
========

A set of reusable components we use in our scala programs

installation
------------

Add this to your SBT config:

```scala
// resolver
val jirafeSnaps = "jirafe.com snaps" at "https://raw.github.com/jirafe/mvn-repo/master/snapshots"

// dependency
val jiraniumPlay = "com.jirafe" %% "jiranium-play" % "1.0-SNAPSHOT"
```


how to publish to jirafe/mvn-repo
---------------------------------

This section is intended to Jirafe developers.

    sbt publish
    cd /path/to/mvn-repo
    cp -r ~/.m2/repository/snapshots/com/jirafe/jiranium-play_2.9.1/* snapshots/com/jirafe/jiranium-play_2.9.1/
    git commit -m "publish jiranium"
    git push
