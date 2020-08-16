import _03_Concatenate_Solution._, _00_Fold_Solution.{foldr,foldl}

object _05_Reverse_Problem extends App {

  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 ∷ [𝛼] → [𝛼]
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 [] = ???
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 (𝑥:𝑥𝑠) = ???
  def reverse[A]: List[A] => List[A] = {
    case Nil     => ???
    case x :: xs => ???
  }
  assert( reverse[Int](List()) == List() )
  assert( reverse[Int](List(1, 2, 3)) == List(3, 2, 1) )

  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 ∷ [𝛼] → [𝛼]
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 = 𝑓𝑜𝑙𝑑𝑟 ??? ???
  { def reverse[A](xs: List[A]): List[A] = foldr(???)(???)(xs)
    assert( reverse[Int](List()) == List() )
    assert( reverse[Int](List(1, 2, 3)) == List(3,2,1)) }

  def snoc[A]: A => List[A] => List[A] = x => xs => ???

  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 ∷ [𝛼] → [𝛼]
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 = 𝑓𝑜𝑙𝑑𝑙 ??? ???
  { def reverse[A](xs: List[A]): List[A] = foldl(???)(???)(xs)
    assert( reverse[Int](List()) == List() )
    assert( reverse[Int](List(1, 2, 3)) == List(3,2,1) ) }

  def cons[A]: List[A] => A => List[A] = xs => x => ???
}
