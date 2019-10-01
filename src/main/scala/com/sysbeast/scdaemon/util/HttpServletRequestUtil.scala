package com.sysbeast.scdaemon.util

import javax.servlet.http.HttpServletRequest
import org.eclipse.jetty.util.StringUtil
import org.json.{JSONArray, JSONObject}

import scala.io.Source

object HttpServletRequestUtil {
  implicit class HttpServletRequestHelper(req: HttpServletRequest) {
    def getLong(param: String): Long = req.getParameter(param).toLong
    def getLong(param: String, default: Long): Long = if(StringUtil.isBlank(req.getParameter(param))) default else getLong(param)
    def getInt(param: String): Int = req.getParameter(param).toInt
    def getInt(param: String, default: Int):Int = if(StringUtil.isBlank(req.getParameter(param))) default else getInt(param)
    def getString(param: String):String = req.getParameter(param)
    def getString(param: String, default: String):String = if(StringUtil.isBlank(req.getParameter(param))) default else getString(param)
    def getBoolean(param: String):Boolean = req.getParameter(param).toBoolean
    def getBoolean(param: String, default: Boolean):Boolean = if(StringUtil.isBlank(req.getParameter(param))) default else getBoolean(param)
    def readBodyText: String = {
      val src = Source.fromInputStream(req.getInputStream, "utf-8")
      val text = src.getLines().toList.mkString("\n")
      src.close
      text
    }
    def readBodyJsonObject: JSONObject = new JSONObject(readBodyText)
    def readBodyJsonArray: JSONArray = new JSONArray(readBodyText)

  }
}
