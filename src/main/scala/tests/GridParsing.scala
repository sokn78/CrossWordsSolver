package tests

import javax.script.ScriptEngineManager
import java.io.File

import datasources.notretemps.{FileParser, NotreTemps}
import entities.{FoundLetter, HorizontalWord, Letters}

import scala.io.Source
import org.json4s.{DefaultFormats, Extraction}
import org.json4s.jackson.JsonMethods.{compact, parse}
import windowing.Output

/**
  * Created by Simon on 23/10/2017.
  */

object GridParsing extends App {

  //Get file from resources folder
  val classLoader = getClass.getClassLoader
  val file = new File(classLoader.getResource("notreTempsGridTest").getFile)

  val ntParser = new datasources.notretemps.FileParser()

  val notreTempsGrid = ntParser.parseFile(file)

  notreTempsGrid.grille.foreach{
    line => println(line.mkString)
  }

  val grid = NotreTemps.convert(notreTempsGrid)


  println(grid)


  println("horizontalWords")
  grid.horizontalWords.foreach{x => x.letters.foreach(println)  ; println() }
  println("verticalWords")
  grid.verticalWords.foreach{x => x.letters.foreach(println)  ; println() }


  println(grid.getLeastMissingLettersWord)

  val blankGrid = grid.makeBlank

  val out = new Output(blankGrid.toHTMLString)

  Thread.sleep(1000)


  val newHorizonTalWord = HorizontalWord(2, Array((0,FoundLetter( Letters.D )),(1,FoundLetter(Letters.M))))

  val filledGrid = blankGrid.fillWithOneWord(newHorizonTalWord)

  out.changeText(blankGrid.toHTMLString)

  Thread.sleep(1000)

  out.changeText(filledGrid.toHTMLString)

  Thread.sleep(1000)

  out.changeText(grid.toHTMLString)

  Thread.sleep(1000)


  out.changeText(blankGrid.toHTMLString)

  println(blankGrid.toString)

  Thread.sleep(1000)

  out.changeText("fin")






}
