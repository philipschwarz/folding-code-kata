import _3_Concatenate_Solution._, _0_Fold_Solution.{foldr,foldl}

object _9_Second_Duality_Theorem extends App {

  // Second duality theorem. This is a generalization of the first.
  // Suppose ⊕, ⊗, and 𝑒 are such that for all 𝑥, 𝑦, and 𝑧 we have
  //   𝑥 ⊕ (𝑦 ⊗ 𝑧)  = (𝑥 ⊕ 𝑦) ⊗ 𝑧
  //   𝑥 ⊕ 𝑒 = 𝑒 ⊗ 𝑥
  // In other words, ⊕ and ⊗ associate with each other, and 𝑒 on the
  // right of ⊕ is equivalent to 𝑒 on the left of ⊗.
  // Then 𝑓𝑜𝑙𝑑𝑟 (⊕) 𝑒 𝑥𝑠 = 𝑓𝑜𝑙𝑑𝑙 (⊗) 𝑒 𝑥𝑠
  val list: List[Int] = List(1, 2, 3)
  val lengthr = foldr(oneplus)(0)_
  val lengthl = foldl(plusOne)(0)_
  def oneplus[A]: A => Int => Int = x => n => 1 + n
  def plusOne[A]: Int => A => Int = n => x => n + 1
  assert( lengthr(list) == lengthl(list) )
  assert( foldr(oneplus)(0)(list) == foldl(plusOne)(0)(list))
  val reverser = foldr(snoc[Int])(Nil)_
  val reversel = foldl(cons[Int])(Nil)_
  def snoc[A]: A => List[A] => List[A] = x => xs => xs ⧺ List(x)
  def cons[A]: List[A] => A => List[A] = xs => x => x::xs
  assert( reverser(list) == reversel(list) )
  assert( foldr(snoc[Int])(Nil)(list) == foldl(cons[Int])(Nil)(list))

}
