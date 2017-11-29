name := """learning-play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  cacheApi,
  guice,
  ehcache,
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
)
