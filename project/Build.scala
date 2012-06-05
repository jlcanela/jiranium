import sbt._
import Keys._

object JiraniumBuild extends Build {

  lazy val play = Project("jiranium-play", file("play")) settings (
    organization := "com.jirafe",
    name := "jiranium-play",
    version := "1.2-SNAPSHOT",
    scalaVersion := "2.9.1",
    libraryDependencies ++= Seq(
      "play" %% "play" % "2.0.1",
      "org.scalaz" %% "scalaz-core" % "6.0.4"),
      scalacOptions := Seq("-deprecation", "-unchecked"),
      publishTo <<= version { (v: String) ⇒
        val base = Path.userHome.absolutePath + "/.m2/repository"
        val local = if (v.trim.endsWith("SNAPSHOT")) base + "/snapshots"
        else base + "/releases"
        Some(Resolver.file("file", new File(local)))
      }
  )
}
