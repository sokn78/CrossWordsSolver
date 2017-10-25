package tests

import javax.script.ScriptEngineManager
import java.io.File

import datasources.notretemps.{FileParser, NotreTemps}

import scala.io.Source
import org.json4s.{DefaultFormats, Extraction}
import org.json4s.jackson.JsonMethods.{compact, parse}

/**
  * Created by Simon on 23/10/2017.
  */

object GridParsing extends App {

  //Get file from resources folder
  val classLoader = getClass.getClassLoader
  val file = new File(classLoader.getResource("notreTempsGridTest").getFile)

  val ntParser = new datasources.notretemps.FileParser()

  val notreTempsGrid = ntParser.parseFile(file)

  val grid = NotreTemps.convert(notreTempsGrid)

  val blankGrid = grid.makeBlank

  println(grid)

  println(blankGrid)

  val horizontalWords = grid.horizontalWords.foreach{x => x.foreach(println)  ; println() }


}
