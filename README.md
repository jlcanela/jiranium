jiranium
========

A set of reusable components we use in our scala programs

how to publish to jirafe/mvn-repo
---------------------------------

This section is intended to Jirafe developers.

    sbt publish
    cd /path/to/mvn-repo
    cp -r ~/.m2/repository/snapshots/com/jirafe/jiranium-play_2.9.1/* snapshots/com/jirafe/jiranium-play_2.9.1/
    git commit -m "publish jiranium"
    git push
