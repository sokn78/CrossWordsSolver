package entities

/**
  * Created by Simon on 23/10/2017.
  */

object Characters extends Enumeration {
  val A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z = Value
}

trait Square {

}

case class FoundCharacter(char:Characters.Value) extends Square
case object WhiteSquare extends Square
case object BlackSquare extends Square