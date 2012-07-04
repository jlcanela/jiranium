import sbt._
import Keys._

object JiraniumBuild extends Build {

  lazy val anorm = Project("jiranium-anorm", file("anorm")) settings (
    organization := "com.jirafe",
    name := "jiranium-anorm",
    version := "1.6",
    scalaVersion := "2.9.1",
    resolvers ++= Seq(
      "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"),
      libraryDependencies ++= Seq(
        "play" %% "play" % "2.0.1",
        "org.scalaz" %% "scalaz-core" % "6.0.4",
        "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
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
