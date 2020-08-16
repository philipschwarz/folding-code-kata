import _0_Fold_Solution.foldr

object _3_Concatenate_Problem extends App {

  // the number of steps required to compute 𝑥𝑠 ⧺ 𝑦𝑠 is proportional
  // to the number of elements in 𝑥𝑠
  // (⧺) ∷ [𝛼] → [𝛼] → [𝛼]
  // [] ⧺ 𝑦𝑠 = ???
  // (𝑥:𝑥𝑠) ⧺ 𝑦𝑠 = ???
  def `(⧺)`[A]: List[A] => List[A] => List[A] =
    xs => ys => xs match {
      case Nil     => ???
      case x :: xs => ???
    }
  // x ⧺ y is syntactic sugar for `(⧺)`(x)(y)
  implicit class Syntax[A](xs: List[A]) { def ⧺(ys: List[A]): List[A] = `(⧺)`(xs)(ys) }
  assert( (List(1, 2, 3) ⧺ List(1, 2, 3)) == List(1, 2, 3, 1, 2, 3) )

  // (⧺) ∷ [𝛼] → [𝛼] → [𝛼]
  // (⧺ 𝑦𝑠) = foldr ??? ???
  { def `(⧺)`[A]: List[A] => List[A] => List[A] = xs => ys => foldr(???)(???)(???)
    def cons[A]: A => List[A] => List[A] = x => xs => ???
    assert( (List(1,2,3) ⧺ List(1, 2, 3)) == List(1, 2, 3, 1, 2, 3) ) }

}
