package methods

/**
  * Created by Simon on 25/10/2017.
  */

object BruteForce {

  def combine[A](xs: Traversable[Traversable[A]]): Seq[Seq[A]] =
    xs.foldLeft(Seq(Seq.empty[A])){
      (x, y) => for (a <- x.view; b <- y) yield a :+ b
    }

  def makeCartesianIterator[A](nb:Int,a:Traversable[A]):Seq[Seq[A]] = {
    combine(List.fill(nb)(a))
  }

}
