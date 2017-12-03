package entities

import entities.Grid
import methods.ArrayManipulation
import entities.WhiteGridSquare

/**
  * Created by Simon on 23/10/2017.
  */


sealed trait Word extends Product with Serializable{
  def letters:Array[(Int,GridSquare)]
}

final case class HorizontalWord(lineNumber:Int, letters:Array[(Int,GridSquare)]) extends Word
final case class VerticalWord(columnNumber:Int, letters:Array[(Int,GridSquare)]) extends Word


case class Grid(vSize:Int, hSize:Int, gridSquares : Array[Array[GridSquare]]){

  /*
  val isComplete: Boolean = !gridSquares.exists{
    _.exists{
      _ == WhiteGridSquare
    }
  }*/

  override def toString:String = {
    gridSquares.map { line =>
      line.mkString(" ")
    }.mkString("\n")
  }

  def toHTMLString:String = {
    "<html>" + gridSquares.map { line =>
      line.mkString(" ")
    }.mkString("<br>") + "</html>"
  }


  def getLeastMissingLettersWord:Option[Word] = {
    allWords.map(word => (word, word.letters.map(_._2).count(_ == WhiteGridSquare))).minBy(_._2)
    match {
      case (_, 0) => None
      case (word,_) => Some(word)
    }
  }

  def makeBlank:Grid =
    Grid(vSize, hSize, gridSquares.map{
      _.map {
        case BlackGridSquare => BlackGridSquare
        case _ => WhiteGridSquare
      }
    }
  )

  val horizontalWords: Array[HorizontalWord] = gridSquares.zipWithIndex.map{
    case (line,lineNumber) =>
      (lineNumber,ArrayManipulation.splitArray[(GridSquare,Int)](line.zipWithIndex,x => x._1 == BlackGridSquare))
  }.flatMap{
    case (lineNumber,allSegments) =>
      allSegments.map( oneSeg => HorizontalWord(lineNumber,oneSeg.map{case (gridSquare,columnNumber) => (columnNumber,gridSquare)}))
  }.filter(_.letters.length > 1)

  val verticalWords: Array[VerticalWord] = gridSquares.transpose.zipWithIndex.map{
    case (line,columnNumber) =>
      (columnNumber,ArrayManipulation.splitArray[(GridSquare,Int)](line.zipWithIndex,x => x._1 == BlackGridSquare))
  }.flatMap{
    case (columnNumber,allSegments) =>
      allSegments.map( oneSeg => VerticalWord(columnNumber,oneSeg.map{case (gridSquare,lineNumber) => (lineNumber,gridSquare)}))
  }.filter(_.letters.length > 1)

  val allWords: Array[Word] = horizontalWords ++ verticalWords

  val isComplete:Boolean = getLeastMissingLettersWord.isEmpty

  def fillWithOneWord(foundWord:Word):Grid = {
    val newGrid: Grid = Grid(vSize,hSize,gridSquares.map(_.clone()).clone())
    foundWord match {
      case HorizontalWord(lineNumber,letters) => letters.foreach{
        case (index,square) => newGrid.gridSquares(lineNumber).update(index,square)
      }
      case VerticalWord(columnNumber,letters) => letters.foreach{
        case (index,square) => newGrid.gridSquares(index).update(columnNumber,square)
      }
    }
    newGrid
  }
}

case class WordList(words:List[Word]){

}
