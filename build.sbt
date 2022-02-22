val ZIOVersion        = "2.0.0-RC2"

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.1"

lazy val root = (project in file("."))
  .settings(
    name := "zionomicon"
  )

libraryDependencies ++= Seq(
  "dev.zio"       %% "zio"          % ZIOVersion,
  "dev.zio"       %% "zio-streams"  % ZIOVersion,
  "dev.zio"       %% "zio-test"     % ZIOVersion % "test",
  "dev.zio"       %% "zio-test-sbt" % ZIOVersion % "test",
)

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
