import sbt._
import Keys._

object JiraniumBuild extends Build {
  
  lazy val play = Project("jiranium-play", file("play")) settings (
    organization := "com.jirafe",
    name := "jiranium-play",
    version := "1.0",
    scalaVersion := "2.9.1",
    libraryDependencies ++= Seq(
      "play" %% "play" % "2.0.1",
      "org.scalaz" %% "scalaz-core" % "6.0.4"
    ),
    scalacOptions := Seq("-deprecation", "-unchecked")
  )
}
