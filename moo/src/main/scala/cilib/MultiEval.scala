package cilib

import scalaz.NonEmptyList

final class MultiEval[A] private (objectives: List[Eval[A]]) {
  def eval(xs: NonEmptyList[A]): List[Objective[A]] =
    objectives.map(_.eval(xs))
}

object MultiEval {

  // The varags sucks
  def apply[A](a: Eval[A], b: Eval[A], rest: Eval[A]*) =
    new MultiEval(List(a, b) ++ rest.toList)

}
