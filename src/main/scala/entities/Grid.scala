package entities

import entities.Grid

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

}

