import scala.annotation.tailrec

object _0_Fold_Problem extends App {

  // 𝑓𝑜𝑙𝑑𝑟 ∷ (𝛼 → 𝛽 → 𝛽) → 𝛽 → [𝛼] → 𝛽
  // 𝑓𝑜𝑙𝑑𝑟 𝑓 𝑒 [] = ???
  // 𝑓𝑜𝑙𝑑𝑟 𝑓 𝑒 (𝑥:𝑥𝑠) = ???
  // 𝑖.𝑒. 𝑓𝑜𝑙𝑑𝑟 (⊕) 𝑒 [𝑥1,𝑥2,𝑥3] = 𝑥1 ⊕ (𝑥2 ⊕ (𝑥3 ⊕ 𝑒))
  def foldr[A,B](f: A => B => B)(v: B)(s: List[A]): B = s match {
    case Nil   => ???
    case x::xs => ???
  }

  // 𝑓𝑜𝑙𝑑𝑙 ∷ (𝛽 → 𝛼 → 𝛽) → 𝛽 → [𝛼] → 𝛽
  // 𝑓𝑜𝑙𝑑𝑙 𝑓 𝑒 [] = ???
  // 𝑓𝑜𝑙𝑑𝑙 𝑓 𝑒 (𝑥:𝑥𝑠) = ???
  // 𝑖.𝑒. 𝑓𝑜𝑙𝑑𝑙 (⊕) 𝑒 [𝑥1,𝑥2,𝑥3] = ((𝑒 ⊕ 𝑥1) ⊕ 𝑥2) ⊕ 𝑥3
  @tailrec
  def foldl[A,B](f: B => A => B)(e: B)(s: List[A]): B = s match {
    case Nil   => ???
    case x::xs => foldl(???)(???)(???)
  }

  // 𝑝𝑟𝑜𝑑∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  { val prod: List[Int] => Int = foldr(mult)(1)
    assert(prod(List(2, 3, 4)) == 24) }

  def mult: Int => Int => Int =
    m => n => m * n

  // 𝑝𝑟𝑜𝑑 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  { val prod: List[Int] => Int = foldl(mult)(1)
    assert(prod(List(2, 3, 4)) == 24) }

}
