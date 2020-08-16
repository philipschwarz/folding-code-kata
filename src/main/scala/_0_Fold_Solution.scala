import scala.annotation.tailrec

object _0_Fold_Solution extends App {

  // 𝑓𝑜𝑙𝑑𝑟 ∷ (𝛼 → 𝛽 → 𝛽) → 𝛽 → [𝛼] → 𝛽
  // 𝑓𝑜𝑙𝑑𝑟 𝑓 𝑒 [] = 𝑒
  // 𝑓𝑜𝑙𝑑𝑟 𝑓 𝑒 (𝑥:𝑥𝑠) = 𝑓 𝑥 (𝑓𝑜𝑙𝑑𝑟 𝑓 𝑒 𝑥𝑠)
  // 𝑖.𝑒. 𝑓𝑜𝑙𝑑𝑟 (⊕) 𝑒 [𝑥1,𝑥2,𝑥3] = 𝑥1 ⊕ (𝑥2 ⊕ (𝑥3 ⊕ 𝑒))
  def foldr[A,B](f: A => B => B)(v: B)(s: List[A]): B = s match {
    case Nil   => v
    case x::xs => f(x)(foldr(f)(v)(xs))
  }

  // 𝑓𝑜𝑙𝑑𝑙 ∷ (𝛽 → 𝛼 → 𝛽) → 𝛽 → [𝛼] → 𝛽
  // 𝑓𝑜𝑙𝑑𝑙 𝑓 𝑒 [] = 𝑒
  // 𝑓𝑜𝑙𝑑𝑙 𝑓 𝑒 (𝑥:𝑥𝑠) = 𝑓𝑜𝑙𝑑𝑙 𝑓 (𝑓 𝑒 𝑥) 𝑥𝑠
  // 𝑖.𝑒. 𝑓𝑜𝑙𝑑𝑙 (⊕) 𝑒 [𝑥1,𝑥2,𝑥3] = ((𝑒 ⊕ 𝑥1) ⊕ 𝑥2) ⊕ 𝑥3
  @tailrec
  def foldl[A,B](f: B => A => B)(e: B)(s: List[A]): B = s match {
    case Nil   => e
    case x::xs => foldl(f)(f(e)(x))(xs)
  }

  // 𝑝𝑟𝑜𝑑 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑝𝑟𝑜𝑑 = 𝑓𝑜𝑙𝑑𝑟 (x) 0
  { val prod: List[Int] => Int = foldr(mult)(1)
    assert(prod(List(2, 3, 4)) == 24) }

  def mult: Int => Int => Int =
    m => n => m * n

  // 𝑝𝑟𝑜𝑑 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑝𝑟𝑜𝑑 = 𝑓𝑜𝑙𝑑𝑙 (x) 0
  { val prod: List[Int] => Int = foldl(mult)(1)
    assert(prod(List(2, 3, 4)) == 24) }

}
