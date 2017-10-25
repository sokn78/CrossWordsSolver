package entities

/**
  * Created by Simon on 23/10/2017.
  */

object Letters extends Enumeration {
  val A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z = Value
  def fromCharacter(s:Character):Value = {
    values.find(value => value.toString.charAt(0) == s)
      .getOrElse(throw new Exception("Unknown character"))
  }
}

trait Square

case class FoundLetter(char:Letters.Value) extends Square {
  override def toString:String = char.toString
}
case object WhiteSquare extends Square{
  override def toString:String = " "
}
case object BlackSquare extends Square{
  override def toString:String = "\u2588"
}