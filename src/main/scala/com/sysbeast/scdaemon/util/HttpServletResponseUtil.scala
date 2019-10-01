package com.sysbeast.scdaemon.util

import javax.servlet.http.HttpServletResponse

object HttpServletResponseUtil {
  implicit class HttpServletResponseHelper(resp: HttpServletResponse) {
    def setPlainAndUtf: Unit = {
      resp.setCharacterEncoding("utf-8")
      resp.setContentType("text/plain")
    }
    def sendJson(result: org.json.JSONObject): Unit = {
      setPlainAndUtf
      resp.setStatus(HttpServletResponse.SC_OK)
      resp.getWriter.println(result.toString())
    }

    def sendJson(result: org.json.JSONArray): Unit = {
      setPlainAndUtf
      resp.setStatus(HttpServletResponse.SC_OK)
      resp.getWriter.println(result.toString())
    }

    def sendString(s: String): Unit = {
      setPlainAndUtf
      resp.setStatus(HttpServletResponse.SC_OK)
      resp.getWriter.print(s)
    }
  }

}
