package entities

import entities.Grid
import methods.ArrayManipulation

/**
  * Created by Simon on 23/10/2017.
  */


case class Grid(vSize:Int, hSize:Int, gridSquares : Array[Array[GridSquare]]){

  override def toString:String = {
    gridSquares.map { line =>
      line.mkString(" ")
    }.mkString("\n")
  }

  def makeBlank:Grid =
    Grid(vSize, hSize, gridSquares.map{
      _.map {
        case BlackGridSquare => BlackGridSquare
        case _ => WhiteGridSquare
      }
    }
  )


  val horizontalWords: Array[Array[(Position, GridSquare)]] = gridSquares.zipWithIndex.map{
    case (line,lineNumber) =>
      (lineNumber,ArrayManipulation.splitArray[(GridSquare,Int)](line.zipWithIndex,x => x._1 == BlackGridSquare))
  }.flatMap{
    case (lineNumber,allSegments) =>
      allSegments.map( _.map{case (gridSquare,columnNumber) => (Position(lineNumber,columnNumber),gridSquare)})
  }

}

case class Position(h:Int,v:Int)

case class WordList(words:List[Array[(Position,GridSquare)]]){

}
