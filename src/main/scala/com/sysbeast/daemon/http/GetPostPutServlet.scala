package com.sysbeast.daemon.http

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

abstract class GetPostPutServlet extends HttpServlet {
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = onDo(req, resp)
  override def doPost(req: HttpServletRequest, resp: HttpServletResponse): Unit = onDo(req, resp)
  override def doPut(req: HttpServletRequest, resp: HttpServletResponse): Unit = onDo(req, resp)
  def onDo(req: HttpServletRequest, resp: HttpServletResponse): Unit
}
