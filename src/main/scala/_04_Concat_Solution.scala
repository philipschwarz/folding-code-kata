import _03_Concatenate_Solution._, _0_Fold_Solution.{foldr,foldl}

object _04_Concat_Solution extends App {

  // 𝑐𝑜𝑛𝑐𝑎𝑡 ∷ [[𝛼]] → [𝛼]
  // 𝑐𝑜𝑛𝑐𝑎𝑡 [] = []
  // 𝑐𝑜𝑛𝑐𝑎𝑡 (𝑥𝑠 : 𝑥𝑠𝑠) = 𝑥𝑠 ⧺ 𝑐𝑜𝑛𝑐𝑎𝑡 𝑥𝑠𝑠
  def concat[A]: List[List[A]] => List[A] = {
    case Nil => Nil
    case xs :: xss => xs ⧺ concat(xss)
  }
  assert( concat(List(List(1, 2), Nil, List(3, 2, 1))) == List(1, 2, 3, 2, 1) )

  // 𝑐𝑜𝑛𝑐𝑎𝑡 ∷ [[𝛼]] → [𝛼]
  // 𝑐𝑜𝑛𝑐𝑎𝑡 = 𝑓𝑜𝑙𝑑𝑟 (⧺) []
  // If the argument to concat is a list of length m consisting
  // lists each of length n the time complexity is O(m x n)
  { def concat[A]: List[List[A]] => List[A] = foldr(`(⧺)`[A])(Nil)
    assert( concat(List(List(1, 2), Nil, List(3, 2, 1))) == List(1, 2, 3, 2, 1) ) }

  // 𝑐𝑜𝑛𝑐𝑎𝑡 ∷ [[𝛼]] → [𝛼]
  // 𝑐𝑜𝑛𝑐𝑎𝑡 = 𝑓𝑜𝑙𝑑𝑙 (⧺) []
  // The time complexity is O(m↑2 x n) i.e. using foldr rather
  // than foldl results in an asymptotically faster program
  { def concat[A]: List[List[A]] => List[A] = foldl(`(⧺)`[A])(Nil)
    assert( concat(List(List(1, 2), Nil, List(3, 2, 1))) == List(1, 2, 3, 2, 1) ) }

}
