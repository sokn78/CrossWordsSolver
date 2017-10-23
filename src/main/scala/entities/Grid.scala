package entities

/**
  * Created by Simon on 23/10/2017.
  */


class Grid(vSize:Int, hSize:Int, val squares : Array[Array[Square]]){
  override def toString:String = {
    squares.map { line =>
      line.mkString(" ")
    }.mkString("\n")
  }
}

