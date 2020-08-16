import _00_Fold_Solution.foldr

object _06_Map_Problem extends App {

  // 𝑚𝑎𝑝 ∷ (𝛼 → 𝛽) → [𝛼] → [𝛽]
  // 𝑚𝑎𝑝 𝑓 [] = ???
  // 𝑚𝑎𝑝 𝑓 (𝑥:𝑥𝑠) = ???
  def map[A,B]: (A => B) => List[A] => List[B] = f => {
    case Nil     => ???
    case x :: xs => ???
  }
  val double: Int => Int = x => 2 * x
  assert( map(double)(List(1, 2, 3)) == List(2, 4, 6) )

  // 𝑚𝑎𝑝 ∷ (𝛼 → 𝛽) → [𝛼] → [𝛽]
  // 𝑚𝑎𝑝 𝑓 = 𝑓𝑜𝑙𝑑𝑟 ??? ???
  { def map[A,B]: (A => B) => List[A] => List[B] = f => foldr(???)(???)_
    def cons[A]: A => List[A] => List[A] = x => xs => ???
    assert( map(double)(List(1, 2, 3)) == List(2, 4, 6) )}
}
