import _03_Concatenate_Solution._
import _00_Fold_Solution.{foldl, foldr}

object _04_Concat_Problem extends App {

  // 𝑐𝑜𝑛𝑐𝑎𝑡 ∷ [[𝛼]] → [𝛼]
  // 𝑐𝑜𝑛𝑐𝑎𝑡 [] = ???
  // 𝑐𝑜𝑛𝑐𝑎𝑡 (𝑥𝑠:𝑥𝑠𝑠) = ???
  def concat[A]: List[List[A]] => List[A] = {
    case Nil       => ???
    case xs :: xss => ???
  }
  assert( concat(List(List(1, 2), Nil, List(3, 2, 1))) == List(1, 2, 3, 2, 1) )

  // 𝑐𝑜𝑛𝑐𝑎𝑡 ∷ [[𝛼]] → [𝛼]
  // 𝑐𝑜𝑛𝑐𝑎𝑡 = 𝑓𝑜𝑙𝑑𝑟 ??? ???
  { def concat[A]: List[List[A]] => List[A] = foldr(???)(???)
    assert( concat(List(List(1, 2), Nil, List(3, 2, 1))) == List(1, 2, 3, 2, 1) )
  }

  // 𝑐𝑜𝑛𝑐𝑎𝑡 ∷ [[𝛼]] → [𝛼]
  // 𝑐𝑜𝑛𝑐𝑎𝑡 = 𝑓𝑜𝑙𝑑𝑙 ??? ???
  { def concat[A]: List[List[A]] => List[A] = foldl(???)(???)
    assert( concat(List(List(1, 2), Nil, List(3, 2, 1))) == List(1, 2, 3, 2, 1) ) }

}
