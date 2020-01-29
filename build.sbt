
// *****************************************************************************
// Projects
// *****************************************************************************

lazy val Version = new {
  val akkaVersion = "2.6.1"
  val akkaActor = akkaVersion
  val akkaHttp = "10.1.10"
  val akkaHttpCors = "0.3.0"
  val akkaStream = akkaVersion
  val cats = "2.0.0"
  val circe = "0.12.2"
  val logback = "1.2.3"
  val scalajHttp = "2.4.0"

  val scalaMock = "4.1.0"
  val scalaMockscalaTest = "3.6.0"

  val scalaTest = "3.0.5"
  val sttp = "1.2.0"

  val scalaLogging = "3.9.2"
}


lazy val core =
    (project in file("core"))
    .settings(commonSettings)
    .settings(
    name := "telegram-core",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core" % Version.circe,
      "io.circe" %% "circe-generic" % Version.circe,
      "io.circe" %% "circe-generic-extras" % Version.circe,
      "io.circe" %% "circe-parser" % Version.circe,
      "io.circe" %% "circe-literal" % Version.circe,
      "org.typelevel" %% "cats-core" % Version.cats,
      "org.typelevel" %% "cats-free" % Version.cats,
      "com.softwaremill.sttp" %% "core" % Version.sttp,

      "org.scalamock" %% "scalamock-scalatest-support" % Version.scalaMockscalaTest % Test,
      "org.scalatest" %% "scalatest" % Version.scalaTest % Test,

      "com.typesafe.scala-logging" %% "scala-logging" % Version.scalaLogging
    )
  )
    .settings(
      libraryDependencies ++= Seq(
        library.sttpOkHttp
      )
    )


lazy val akka: Project =
  project
    .in(file("akka"))
    .settings(commonSettings: _*)
    .settings(name := "telegram-akka")
    .settings(
      libraryDependencies ++= Seq(
        library.akkaHttp,
        library.akkaActor,
        library.akkaStream,
        library.akkaHttpCors
      )
    )
  .dependsOn(core)


// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {

    val akkaHttp = "com.typesafe.akka" %% "akka-http" % Version.akkaHttp
    val akkaHttpTestkit = "com.typesafe.akka" %% "akka-http-testkit" % Version.akkaHttp
    val akkaActor = "com.typesafe.akka" %% "akka-actor" % Version.akkaActor
    val akkaStream = "com.typesafe.akka" %% "akka-stream" % Version.akkaStream

    val scalajHttp = "org.scalaj" %% "scalaj-http" % Version.scalajHttp
    val scalaMock = "org.scalamock" %% "scalamock-scalatest-support" % Version.scalaMock
    val akkaHttpCors = "ch.megard" %% "akka-http-cors" % Version.akkaHttpCors
    val scalaTest = "org.scalatest" %% "scalatest" % Version.scalaTest

    val logback = "ch.qos.logback" % "logback-classic" % Version.logback
    val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % Version.scalaLogging

    val sttpOkHttp = "com.softwaremill.sttp" %% "okhttp-backend" % Version.sttp
  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val commonSettings =
  Seq(
    organization := "com.bot4s",
    organizationName := "Alfonso² Peterssen",
    scalaVersion := "2.12.10",
    crossScalaVersions := Seq(scalaVersion.value, "2.12.10"),
    startYear := Some(2015),
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-encoding", "UTF-8",
      "-feature",
      "-unchecked",
      //"-Xfatal-warnings",
      "-Xlint:_",
      "-Yno-adapted-args",
      "-Ywarn-dead-code"
    ),
    shellPrompt in ThisBuild := { state =>
      val project = Project.extract(state).currentRef.project
      s"[$project]> "
    },
    version := "4.4.0-RC3-sevts",
    publishMavenStyle := true
  )

//lazy val publishSettings =
//  Seq(
//    homepage := Some(url("https://github.com/mukel/telegrambot4s")),
//    scmInfo := Some(ScmInfo(url("https://github.com/mukel/telegrambot4s"),
//      "git@github.com:mukel/telegrambot4s.git")),
//    developers += Developer("mukel",
//      "Alfonso² Peterssen",
//      "mukel@users.noreply.github.com",
//      url("https://github.com/mukel")),
//
//    pomIncludeRepository := (_ => false),
//    publishTo := Some(
//      if (isSnapshot.value)
//        Opts.resolver.sonatypeSnapshots
//      else
//        Opts.resolver.sonatypeStaging
//    ),
//    publishArtifact in Test := false,
//    publishMavenStyle := true,
//
//    // sbt-release
//    releaseCrossBuild := true,
//    releasePublishArtifactsAction := PgpKeys.publishSigned.value,
//    releaseIgnoreUntrackedFiles := true,
//    releaseProcess := TelegramBot4sRelease.steps
//  )

lazy val doNotPublish = Seq()
//  Seq(
//    publishArtifact := false,
//    skip in publish := true
//  )
