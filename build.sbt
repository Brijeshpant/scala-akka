name := "scala-akka"

version := "1.0"

scalaVersion := "2.12.1"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.16"

libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "2.4.16"
