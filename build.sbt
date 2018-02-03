import sbt._
import sbt.Keys._

name := "scala-inmemory-data-stores"

version := "0.0.1-SNAPSHOT"
libraryDependencies ++= Dependencies.additionalDependencies
scalaVersion := "2.11.11"

cancelable in Global := true
scalafmtOnCompile in ThisBuild := true
