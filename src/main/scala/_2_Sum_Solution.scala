import _0_Fold_Solution.{foldr,foldl}

object _2_Sum_Solution extends App {

  // 𝑠𝑢𝑚 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑠𝑢𝑚 [] = 0
  // 𝑠𝑢𝑚 (𝑥:𝑥𝑠) = 𝑥 + (𝑠𝑢𝑚 𝑥𝑠)
  def sum: List[Int] => Int = {
    case Nil     => 0
    case x :: xs => x + sum(xs)
  }
  assert( sum(List(1, 2, 3)) == 6 )

  // 𝑠𝑢𝑚 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑠𝑢𝑚 = 𝑓𝑜𝑙𝑑𝑟 (+) 0
  { val sum: List[Int] => Int = foldr(plus)(0)
    assert(sum(List(1, 2, 3)) == 6) }

  def plus: Int => Int => Int =
    m => n => m + n

  // 𝑠𝑢𝑚 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑠𝑢𝑚 = 𝑓𝑜𝑙𝑑𝑙 (+) 0
  { val sum: List[Int] => Int = foldl(plus)(0)
    assert(sum(List(1, 2, 3)) == 6) }
}
