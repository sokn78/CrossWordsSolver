package datasources.notretemps

/**
  * Created by Simon on 23/10/2017.
  */

import java.io.File
import javax.script.ScriptEngineManager

import entities.Grid

import scala.io.Source
import org.json4s.{DefaultFormats, Extraction}
import org.json4s.jackson.JsonMethods.{compact, parse}

case class NotreTemps(grille:List[List[String]], definitionsh:List[List[String]], definitionsv:List[List[String]],nbcaseslargeur:Int,nbcaseshauteur:Int)

class FileParser() {
  implicit val formats: DefaultFormats.type = DefaultFormats

  val engine = new ScriptEngineManager().getEngineByMimeType("text/javascript")
  private val NotreTempsResourcePattern = "var gamedata = (.*);".r

  def parseFile(file: File): NotreTemps = {

    val content: String = Source.fromFile(file).getLines().mkString("")
    val parsing = content match {
      case NotreTempsResourcePattern(body) => body
    }
    parse(engine.eval("JSON.stringify(" + parsing + ")").toString)
      .extract[NotreTemps]
  }
}

object NotreTemps  {

  def convert(notreTempsGrid:NotreTemps):Grid = {

    val vSize = notreTempsGrid.nbcaseshauteur
    val hSize = notreTempsGrid.nbcaseslargeur

    throw new Exception("Not Implemented")
  }

}




