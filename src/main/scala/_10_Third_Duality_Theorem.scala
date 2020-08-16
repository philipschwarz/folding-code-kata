import _0_Fold_Solution.{foldr,foldl}

object _10_Third_Duality_Theorem extends App {

  // Third duality theorem. For all finite lists 𝑥𝑠,
  // 𝑓𝑜𝑙𝑑𝑟 𝑓 𝑒 𝑥𝑠 = 𝑓𝑜𝑙𝑑𝑙 (𝑓𝑙𝑖𝑝 𝑓) 𝑒 (𝑟𝑒𝑣𝑒𝑟𝑠𝑒 𝑥𝑠)
  //              𝑤ℎ𝑒𝑟𝑒 𝑓𝑙𝑖𝑝 𝑓 𝑥 𝑦 = 𝑓 𝑦 𝑥
  val sumr = foldr(plus)(0)_
  val suml = (xs: List[Int]) => foldl(flip(plus))(0)(xs.reverse)
  def flip[A, B, C]: (A => B => C) => (B => A => C) = f => b => a => f(a)(b)
  def plus: Int => Int => Int = m => n => m + n
  val list: List[Int] = List(1, 2, 3)
  assert( sumr(list) == suml(list) )
  assert( foldr(plus)(0)(list) == foldl(flip(plus))(0)(list.reverse) )

  def oneplus[A]: A => Int => Int = x => n => 1 + n
  val lengthr = foldr(oneplus)(0)_
  def lengthl[A] = (xs: List[A]) => foldl(flip(oneplus))(0)(xs.reverse)
  assert( lengthr(list) == lengthl(list) )
  assert( foldr(oneplus)(0)(list) == foldl(flip(oneplus))(0)(list.reverse) )

  val decimalr = foldr(`(⊕)`)(0)_
  val decimall = (xs: List[Int]) => foldl(flip(`(⊕)`))(0)(xs.reverse)
  def `(⊕)`: Int => Int => Int = x => n => 10 * n + x
  assert( decimalr(list) == decimall(list) )
  assert( foldr(`(⊕)`)(0)(list) == foldl(flip(`(⊕)`))(0)(list.reverse) )

}
