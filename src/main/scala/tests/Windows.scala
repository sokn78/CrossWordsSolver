package tests

import windowing._


object Windows extends App{

  /*

  val f = new Output("Suspense ...")

  Thread.sleep(2000)
  f.changeText("On est les meilleurs !")

*/

  val a = Array(1,2,3)

  a.foreach(println)

  val g = a.clone()

  g.update(2,8)


  a.foreach(println)




}
