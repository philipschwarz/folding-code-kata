import _0_Fold_Solution.{foldr,foldl}

object _2_Sum_Problem extends App {

  // 𝑠𝑢𝑚 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑠𝑢𝑚 [] = ???
  // 𝑠𝑢𝑚 (𝑥:𝑥𝑠) = ???
  def sum: List[Int] => Int = {
    case Nil     => ???
    case x :: xs => ???
  }
  assert( sum(List(1, 2, 3)) == 6 )

  // 𝑠𝑢𝑚 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑠𝑢𝑚 = 𝑓𝑜𝑙𝑑𝑟 ??? ???
  { val sum: List[Int] => Int = foldr(???)(???)
    assert(sum(List(1, 2, 3)) == 6) }

  def plus: Int => Int => Int =
    m => n => ???

  // 𝑠𝑢𝑚 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑠𝑢𝑚 = 𝑓𝑜𝑙𝑑𝑟 ??? ???
  { val sum: List[Int] => Int = foldl(???)(???)
    assert(sum(List(1, 2, 3)) == 6) }
}
