import _0_Fold_Solution.{foldr,foldl}

object _08_First_Duality_Theorem extends App {

  // First duality theorem. Suppose (⊕) is associative with unit 𝑒.
  // Then 𝑓𝑜𝑙𝑑𝑟 (⊕) 𝑒 𝑥𝑠 = 𝑓𝑜𝑙𝑑𝑙 (⊕) 𝑒 𝑥𝑠
  def plus: Int => Int => Int = m => n => m + n
  val sumr = foldr(plus)(0)_
  val suml = foldl(plus)(0)_
  val list: List[Int] = List(1, 2, 3)
  assert( sumr(list) == suml(list) )
  assert( foldr(plus)(0)(list) == foldl(plus)(0)(list) )

}
