import sbt._
import Keys._

object JiraniumBuild extends Build {

  lazy val anorm = Project("jiranium-anorm", file("anorm")) settings (
    organization := "com.jirafe",
    name := "jiranium-anorm",
    version := "2.0",
    scalaVersion := "2.9.1",
    resolvers ++= Seq(
      "Typesafe releases" at "http://repo.typesafe.com/typesafe/releases/",
      "Typesafe snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"),
      libraryDependencies ++= Seq(
        "play" %% "anorm" % "2.1-SNAPSHOT",
        "joda-time" % "joda-time" % "2.0",
        "org.joda" % "joda-convert" % "1.2",
        "org.scalaz" %% "scalaz-core" % "6.0.4",
        "org.specs2" %% "specs2" % "1.11" % "test"),
        scalacOptions := Seq("-deprecation", "-unchecked"),
        publishTo <<= version { (v: String) ⇒
          val base = Path.userHome.absolutePath + "/.m2/repository"
          val local = if (v.trim.endsWith("SNAPSHOT")) base + "/snapshots"
          else base + "/releases"
          Some(Resolver.file("file", new File(local)))
        }
  )
}
