import _00_Fold_Solution.{foldl, foldr}

object _01_Length_Solution extends App {

  // 𝑙𝑒𝑛𝑔𝑡ℎ ∷  [𝛼] → 𝐼𝑛𝑡
  // 𝑙𝑒𝑛𝑔𝑡ℎ [] = 0
  // 𝑙𝑒𝑛𝑔𝑡ℎ (𝑥 : 𝑥𝑠) = 1 + 𝑙𝑒𝑛𝑔𝑡ℎ 𝑥𝑠
  def length[A]: List[A] => Int = {
    case Nil     => 0
    case x :: xs => 1 + length(xs)
  }
  assert(length(List(1, 2, 3)) == 3)

  // 𝑙𝑒𝑛𝑔𝑡ℎ ∷ [𝛼] → 𝐼𝑛𝑡
  // 𝑙𝑒𝑛𝑔𝑡ℎ = 𝑓𝑜𝑙𝑑𝑟 𝑜𝑛𝑒𝑝𝑙𝑢𝑠 0
  { def length[A]: List[A] => Int = foldr(oneplus)(0)
    assert(length[Int](List(1, 2, 3)) == 3) }

  def oneplus[A]: A => Int => Int =
    x => n => 1 + n

  // 𝑙𝑒𝑛𝑔𝑡ℎ ∷ [𝛼] → 𝐼𝑛𝑡
  // 𝑙𝑒𝑛𝑔𝑡ℎ = 𝑓𝑜𝑙𝑑𝑙 𝑝𝑙𝑢𝑠𝑜𝑛𝑒 0
  { def length[A]: List[A] => Int = foldl(plusOne)(0)
    assert(length[Int](List(1, 2, 3)) == 3) }

  def plusOne[A]: Int => A => Int =
    n => x => n + 1
}
