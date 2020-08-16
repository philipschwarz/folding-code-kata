import _0_Fold_Solution.foldr

object _06_Map_Solution extends App {

  // 𝑚𝑎𝑝 ∷  (𝛼 → 𝛽) → [𝛼] → [𝛽]
  // 𝑚𝑎𝑝 𝑓 [] =  []
  // 𝑚𝑎𝑝 𝑓 (𝑥:𝑥𝑠) = 𝑓 𝑥 : 𝑚𝑎𝑝 𝑓 𝑥𝑠
  def map[A,B]: (A => B) => List[A] => List[B] = f => {
    case Nil     => Nil
    case x :: xs => f(x) :: map(f)(xs)
  }
  val double: Int => Int = x => 2 * x
  assert( map(double)(List(1, 2, 3)) == List(2, 4, 6) )

  // 𝑚𝑎𝑝 ∷ (𝛼 → 𝛽) → [𝛼] → [𝛽]
  // 𝑚𝑎𝑝 𝑓 = 𝑓𝑜𝑙𝑑𝑟 (𝑐𝑜𝑛𝑠∙𝑓) []
  //         𝑤ℎ𝑒𝑟𝑒 𝑐𝑜𝑛𝑠 𝑥 𝑥𝑠 = 𝑥 : 𝑥𝑠
  { def map[A,B]: (A => B) => List[A] => List[B] = f => foldr(cons compose f)(Nil)_
    def cons[A]: A => List[A] => List[A] = x => xs => x :: xs
    assert( map(double)(List(1, 2, 3)) == List(2, 4, 6) )}
}
