package tests

import javax.script.ScriptEngineManager
import java.io.File

import datasources.notretemps.NotreTemps

import scala.io.Source
import org.json4s.{DefaultFormats, Extraction}
import org.json4s.jackson.JsonMethods.{compact, parse}

/**
  * Created by Simon on 23/10/2017.
  */

object GridParsing extends App {

  val engine = new ScriptEngineManager().getEngineByMimeType("text/javascript")


  //Get file from resources folder
  val classLoader = getClass.getClassLoader
  val file = new File(classLoader.getResource("notreTempsGridTest").getFile)

  val content: String = Source.fromFile(file).getLines().mkString("")

  private val NotreTempsResourcePattern = "var gamedata = (.*);".r

  //private val ResponsePattern = "(.*)call_ids = List\\((.*)\\) response \\(200 OK\\) = (.*)".r

  val parsing = content match {
    case NotreTempsResourcePattern(body) => body
  }

  println(parsing)

  val result = engine.eval("JSON.stringify(" + parsing + ")").toString

  println(result)

  implicit val formats: DefaultFormats.type = DefaultFormats

  val nt = parse(result).extract[NotreTemps]







  /*




  println(result)

*/



}
