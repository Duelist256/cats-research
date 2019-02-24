name := "cats-sandbox"

version := "0.1"

scalaVersion := "2.12.5"

scalacOptions ++= Seq("-Ypartial-unification", "-Xfatal-warnings")

libraryDependencies += "org.typelevel" %% "cats-core" % "1.6.0"