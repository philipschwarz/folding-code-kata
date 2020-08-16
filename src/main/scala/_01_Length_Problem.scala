import _00_Fold_Solution.{foldr,foldl}

object _01_Length_Problem extends App {

  // 𝑙𝑒𝑛𝑔𝑡ℎ ∷ [𝛼] → 𝐼𝑛𝑡
  // 𝑙𝑒𝑛𝑔𝑡ℎ [] = ???
  // 𝑙𝑒𝑛𝑔𝑡ℎ (𝑥:𝑥𝑠) = ???
  def length[A]: List[A] => Int = {
    case Nil     => ???
    case x :: xs => ???
  }
  assert(length(List()) == 0)
  assert(length(List(1, 2, 3)) == 3)

  // 𝑙𝑒𝑛𝑔𝑡ℎ ∷ [𝛼] → 𝐼𝑛𝑡
  // 𝑙𝑒𝑛𝑔𝑡ℎ = 𝑓𝑜𝑙𝑑𝑟 ??? ???
  { def length[A]: List[A] => Int = foldr(???)(???)
    assert(length(List()) == 0)
    assert(length[Int](List(1, 2, 3)) == 3) }

  def oneplus[A]: A => Int => Int =
    x => n => ???

  // 𝑙𝑒𝑛𝑔𝑡ℎ ∷ [𝛼] → 𝐼𝑛𝑡
  // 𝑙𝑒𝑛𝑔𝑡ℎ = 𝑓𝑜𝑙𝑑𝑙 ??? ???
  { def length[A]: List[A] => Int = foldl(???)(???)
    assert(length(List()) == 0)
    assert(length[Int](List(1, 2, 3)) == 3) }

  def plusOne[A]: Int => A => Int =
    n => x => ???
}
