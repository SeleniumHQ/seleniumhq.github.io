ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

libraryDependencies += "org.scalatestplus" %% "selenium-4-1" % "3.2.10.0" % "test"
libraryDependencies += "org.scalatest" %% "scalatest-shouldmatchers" % "3.3.0-SNAP3" % "test"
libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.3.0-SNAP3" % "test"
libraryDependencies += "io.github.bonigarcia" % "webdrivermanager" % "5.0.3"



lazy val root = (project in file("."))
  .settings(
    name := "scala"
  )
