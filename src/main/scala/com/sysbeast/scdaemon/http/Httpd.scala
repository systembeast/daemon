package com.sysbeast.scdaemon.http

import java.io.File


import com.sysbeast.scutil.ClassUtil

import javax.servlet.http.HttpServlet
import javax.websocket.ClientEndpoint
import javax.websocket.server.ServerEndpoint
import org.eclipse.jetty.server.{HttpConfiguration, SecureRequestCustomizer, Server}
import org.eclipse.jetty.server.handler.{HandlerList, ResourceHandler}
import org.eclipse.jetty.servlet.{ServletContextHandler, ServletHandler}
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer
import scala.jdk.CollectionConverters._


class Httpd(www: File) {
  val file_send_cache = new File("file_send_cache")
  val srv = new Server(9000)
  val https = new HttpConfiguration
  https.addCustomizer(new SecureRequestCustomizer)
  //val sslContextFactory = new SslContextFactory
  val sh = new ServletHandler();
  val mirrorRoot = new File(".")
  val sch = new ServletContextHandler(ServletContextHandler.SESSIONS);
  ClassUtil.findClasses(classOf[HttpServlet],  new File(this.getClass.getProtectionDomain.getCodeSource().getLocation().getFile)).foreach {
    c =>
      println("adding servlet " + c)
      sch.addServlet(c, "/" + c.getSimpleName)
  }
  //sch.addServlet(classOf[admin_index], "/admin")

  val resHandler = new ResourceHandler
  resHandler.setDirectoriesListed(true)
  resHandler.setResourceBase(www.getAbsolutePath)
  resHandler.setCacheControl("no-store,no-cache,must-revalidate")
  val hs  = new HandlerList();

  //val check = new CheckHandler();
  //check.setServer(srv)

  //hs.addHandler(check)
  hs.addHandler(resHandler)
  hs.addHandler(sch)

  srv.setHandler(hs)
  srv.setAttribute("org.eclipse.jetty.server.Request.maxFormContentSize", 80000000)
  srv.getAttributeNames.asScala.toList.foreach {
    n =>
      println(n, srv.getAttribute(n)  )
  }
  val wscontainer = WebSocketServerContainerInitializer.configureContext(sch)
  ClassUtil.findClasses(classOf[Any],  new File(this.getClass.getProtectionDomain.getCodeSource().getLocation().getFile))
    .filter(c=>c.isAnnotationPresent(classOf[ServerEndpoint]) && c.isAnnotationPresent(classOf[ClientEndpoint]))
    .foreach {
      c =>
        println("adding websocket " + c)
        wscontainer.addEndpoint(c)
    }
  println("starting...")
  srv.start
  srv.join
}
