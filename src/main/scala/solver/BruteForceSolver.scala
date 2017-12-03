package solver

import entities._
import methods.BruteForce

/**
  * Created by Simon on 27/10/2017.
  */


class BruteForceSolver {


  def makeValidator(solution:Word): Seq[GridSquare] => Boolean = {
    submission => submission.zip(solution.letters).forall{
      case (submittedLetter,realLetter) => submittedLetter == realLetter._2
    }
  }


  def solveBruteForceWord(currentState:Word, solution:Word):Word = {

    val validator = makeValidator(solution)

    val missingIndexes = currentState.letters.zipWithIndex.collect{case((_,WhiteGridSquare),index) => index }

    val bfi: Seq[Seq[Letters.Value]] = BruteForce.makeCartesianIterator(missingIndexes.length, Letters.values)

    val trial = currentState.letters.map(_._2)

    val brokenCode = bfi.find{
      iteration => {
        iteration.zipWithIndex.foreach{
          case (testedLetter,index) => trial.update(missingIndexes(index),FoundLetter(testedLetter))
        }
        validator(trial)
      }
    }.getOrElse(throw new Exception("Cannot break the code."))

    /*
    currentState match {
      case HorizontalWord(columnNumber,letters) => HorizontalWord(columnNumber, letters.zip(brokenCode).map{      }     )
    }
*/

    throw new Exception()

  }

}
