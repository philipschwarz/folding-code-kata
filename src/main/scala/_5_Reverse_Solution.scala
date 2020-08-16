import _3_Concatenate_Solution._, _0_Fold_Solution.{foldl, foldr}

object _5_Reverse_Solution extends App {

  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 ∷  [𝛼] → [𝛼]
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 [] = []
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 (𝑥 : 𝑥𝑠) = 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 𝑥𝑠 ⧺ [𝑥]
  def reverse[A]: List[A] => List[A] = {
    case Nil     => Nil
    case x :: xs => reverse(xs) ⧺ List(x)
  }
  assert( reverse[Int](List(1, 2, 3)) == List(3, 2, 1) )
  // The above definition is not very efficient: on  a list of length
  // 𝑛, it will need a number of reduction steps proportional to 𝑛↑2 to
  // deliver the reversed list. The first element will be appended to
  // the end of a list of length (𝑛 −1), which will take about (𝑛 −1)
  // steps, the second element will be appended to a list of length
  // (𝑛 −2), taking (𝑛 −2)  steps, and so on. The total time is
  // therefore about (𝑛 − 1) + (𝑛 − 2) + … 1 = 𝑛 (𝑛 − 1)/2  steps

  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 takes time proportional to 𝑛↑2 on a list of length 𝑛
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 ∷ [𝛼] → [𝛼]
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 = 𝑓𝑜𝑙𝑑𝑟 𝑠𝑛𝑜𝑐 []
  //         𝑤ℎ𝑒𝑟𝑒 𝑠𝑛𝑜𝑐 𝑥 𝑥𝑠 = 𝑥𝑠 ⧺ [𝑥]
  // e.g. 𝑓𝑜𝑙𝑑𝑟 𝑠𝑛𝑜𝑐 [] [𝑥1,𝑥2,𝑥3] = 𝑠𝑛𝑜𝑐 𝑥1 (𝑠𝑛𝑜𝑐 𝑥2 (𝑠𝑛𝑜𝑐 𝑥3 ⊕ [])) = [𝑥3,𝑥2,𝑥1]
  { def reverse[A](xs: List[A]): List[A] = foldr(snoc[A])(Nil)(xs)
    assert( reverse[Int](List(1, 2, 3)) == List(3,2,1)) }

  def snoc[A]: A => List[A] => List[A] = x => xs => xs ⧺ List(x)

  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 takes time proportional to 𝑛 on a list of length 𝑛
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 ∷ [𝛼] → [𝛼]
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 = 𝑓𝑜𝑙𝑑𝑙 𝑐𝑜𝑛𝑠 []
  //         𝑤ℎ𝑒𝑟𝑒 𝑐𝑜𝑛𝑠 𝑥𝑠 𝑥 = 𝑥 : 𝑥𝑠
  // e.g. 𝑓𝑜𝑙𝑑𝑙 𝑐𝑜𝑛𝑠 [] [𝑥1,𝑥2,𝑥3] = 𝑐𝑜𝑛𝑠 (𝑐𝑜𝑛𝑠 (𝑐𝑜𝑛𝑠 [] 𝑥1) 𝑥2) 𝑥3 = [𝑥3,𝑥2,𝑥1]
  { def reverse[A](xs: List[A]): List[A] = foldl(cons[A])(Nil)(xs)
    assert( reverse[Int](List(1, 2, 3)) == List(3,2,1) ) }

  def cons[A]: List[A] => A => List[A] = xs => x => x::xs
}
