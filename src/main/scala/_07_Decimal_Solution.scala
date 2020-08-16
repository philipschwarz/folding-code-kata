import _0_Fold_Solution.{foldl, foldr}

object _07_Decimal_Solution extends App {

  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 [] = 0
  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 𝑥𝑠 = 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 (𝑖𝑛𝑖𝑡 𝑥𝑠) * 10 + (𝑙𝑎𝑠𝑡 𝑥𝑠)
  val decimal: List[Int] => Int = {
    case Nil => 0
    case xs  => decimal(xs.init) * 10 + xs.last
  }
  assert( decimal(List(3, 4, 5)) == 345 )

  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 = 𝑓𝑜𝑙𝑑𝑟 (⊕) 0
  { def decimal: List[Int] => Int = xs => foldr(`(⊕)`)(0)(xs.reverse)
    def `(⊕)`: Int => Int => Int = x => n => 10 * n + x
    assert( decimal(List(3, 4, 5)) == 345 ) }

  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 = 𝑓𝑜𝑙𝑑𝑙 (⊕) 0
  { def decimal: List[Int] => Int = foldl(`(⊕)`)(0)_
    def `(⊕)`: Int => Int => Int = n => x => 10 * n + x
    assert( decimal(List(3, 4, 5)) == 345 ) }
}
