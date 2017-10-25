package methods

/**
  * Created by Simon on 25/10/2017.
  */
object ArrayManipulation {

  def splitArray[T](xs: Array[T], sepCondition: T => Boolean): List[Array[T]] = {
    var (res, i) = (List[Array[T]](), 0)

    while (i < xs.length) {
      var j = xs.indexWhere(sepCondition, i)
      if (j == -1) j = xs.length
      if (j != i) res ::= xs.slice(i, j)
      i = j + 1
    }

    res.reverse
  }
}
