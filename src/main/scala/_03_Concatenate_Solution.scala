import _00_Fold_Solution.foldr

object _03_Concatenate_Solution extends App {

  // the number of steps required to compute 𝑥𝑠 ⧺ 𝑦𝑠 is proportional
  // to the number of elements in 𝑥𝑠
  // (⧺) ∷ [𝛼] → [𝛼] → [𝛼]
  // [] ⧺ 𝑦𝑠 = 𝑦𝑠
  // (𝑥 : 𝑥𝑠) ⧺ 𝑦𝑠 = 𝑥 : (𝑥𝑠 ⧺ 𝑦𝑠)
  def `(⧺)`[A]: List[A] => List[A] => List[A] =
    xs => ys => xs match {
      case Nil     => ys
      case x :: xs => x :: xs ⧺ ys
    }
  // x ⧺ y is syntactic sugar for `(⧺)`(x)(y)
  implicit class Syntax[A](xs: List[A]) { def ⧺(ys: List[A]): List[A] = `(⧺)`(xs)(ys) }
  assert( (List(1, 2, 3) ⧺ List(1, 2, 3)) == List(1, 2, 3, 1, 2, 3) )

  // (⧺) ∷ [𝛼] → [𝛼] → [𝛼]
  // (⧺ 𝑦𝑠) = 𝑓𝑜𝑙𝑑𝑟 (:) 𝑦𝑠
  { def `(⧺)`[A]: List[A] => List[A] => List[A] = xs => ys => foldr(cons[A])(ys)(xs)
    def cons[A]: A => List[A] => List[A] = x => xs => x :: xs
    assert( (List(1,2,3) ⧺ List(1, 2, 3)) == List(1, 2, 3, 1, 2, 3) ) }

}
