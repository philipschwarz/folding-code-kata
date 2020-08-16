import _00_Fold_Solution.{foldr,foldl}

object _07_Decimal_Problem extends App {

  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 [] = ??
  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 𝑥𝑠 = ???
  val decimal: List[Int] => Int = {
    case Nil => ???
    case xs  => ???
  }
  assert( decimal(List(3, 4, 5)) == 345 )

  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 = 𝑓𝑜𝑙𝑑𝑟 ??? ???
  { def decimal: List[Int] => Int = xs => foldr(???)(???)(???)
    def `(⊕)`: Int => Int => Int = x => n => ???
    assert( decimal(List(3, 4, 5)) == 345 ) }

  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 = 𝑓𝑜𝑙𝑑𝑙 ??? ???
  { def decimal: List[Int] => Int = foldl(???)(???)_
    def `(⊕)`: Int => Int => Int = n => x => ???
    assert( decimal(List(3, 4, 5)) == 345 ) }
}
