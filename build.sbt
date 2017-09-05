name := "tensia"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.0"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.0"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "org.nd4j" % "nd4j-native-platform" % "0.9.1"

target in javah := baseDirectory.value / "native" / "src" / "jni"
