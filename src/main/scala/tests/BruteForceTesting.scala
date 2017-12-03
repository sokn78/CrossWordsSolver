package tests

import entities.Letters
import methods.BruteForce

/**
  * Created by Simon on 25/10/2017.
  */
object BruteForceTesting extends App {


  


  val bTest = List(Letters.A,Letters.B,Letters.C, Letters.D)

  val it1 = BruteForce.makeCartesianIterator(4,bTest)

  it1.take(1000).foreach(println)




}
