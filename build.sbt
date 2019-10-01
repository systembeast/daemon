name := "daemon"
version := "0.1"
scalaVersion := "2.13.1"
libraryDependencies += "org.eclipse.jetty" % "jetty-server" % "9.4.20.v20190813"
libraryDependencies += "org.eclipse.jetty" % "jetty-servlet" % "9.4.20.v20190813"
libraryDependencies += "org.eclipse.jetty" % "jetty-util" % "9.4.20.v20190813"
libraryDependencies += "org.eclipse.jetty.websocket" % "websocket-server" % "9.4.20.v20190813"
libraryDependencies += "org.eclipse.jetty" % "jetty-servlets" % "9.4.20.v20190813"
libraryDependencies += "org.eclipse.jetty" % "jetty-http" % "9.4.20.v20190813"
libraryDependencies += "org.eclipse.jetty" % "jetty-security" % "9.4.20.v20190813"
libraryDependencies += "javax.websocket" % "javax.websocket-api" % "1.1" % "provided"
libraryDependencies += "org.eclipse.jetty.websocket" % "javax-websocket-client-impl" % "9.4.20.v20190813"
libraryDependencies += "org.eclipse.jetty.websocket" % "javax-websocket-server-impl" % "9.4.20.v20190813"
libraryDependencies += "org.json" % "json" % "20190722"

lazy val scutilProject =  RootProject(uri("https://github.com/systembeast/scutil.git"))
lazy val root = Project("root", file(".")) dependsOn(scutilProject)
lazy val `some-api` = (project in file("."))
  .dependsOn(scutilProject)