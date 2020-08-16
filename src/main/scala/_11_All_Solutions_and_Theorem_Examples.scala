import scala.annotation.tailrec

object _11_All_Solutions_and_Theorem_Examples extends App {

  ///////////////////////////
  // Recursive definitions //
  ///////////////////////////

  ///////////////////////////////////////////////////////////////////
  // 𝑠𝑢𝑚 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑠𝑢𝑚 [] = 0
  // 𝑠𝑢𝑚 (𝑥:𝑥𝑠) = 𝑥 + (𝑠𝑢𝑚 𝑥𝑠)
  def sum: List[Int] => Int = {
    case Nil => 0
    case x :: xs => x + sum(xs)
  }
  assert(sum(List()) == 0)
  assert(sum(List(1, 2, 3)) == 6)

  ///////////////////////////////////////////////////////////////////
  // 𝑙𝑒𝑛𝑔𝑡ℎ ∷ [𝛼] → 𝐼𝑛𝑡
  // 𝑙𝑒𝑛𝑔𝑡ℎ [] = 0
  // 𝑙𝑒𝑛𝑔𝑡ℎ (𝑥:𝑥𝑠) = 1 + 𝑙𝑒𝑛𝑔𝑡ℎ 𝑥𝑠
  def length[A]: List[A] => Int = {
    case Nil => 0
    case x :: xs => 1 + length(xs)
  }
  assert(length(List()) == 0)
  assert(length(List(1, 2, 3)) == 3)

  ///////////////////////////////////////////////////////////////////
  // the number of steps required to compute 𝑥𝑠 ⧺ 𝑦𝑠 is proportional
  // to the number of elements in 𝑥𝑠
  // (⧺) ∷ [𝛼] → [𝛼] → [𝛼]
  // [] ⧺ 𝑦𝑠 = 𝑦𝑠
  // (𝑥:𝑥𝑠) ⧺ 𝑦𝑠 = 𝑥 : (𝑥𝑠 ⧺ 𝑦𝑠)
  def `(⧺)`[A]: List[A] => List[A] => List[A] =
    xs => ys => xs match {
      case Nil     => ys
      case x :: xs => x :: xs ⧺ ys
    }
  // x ⧺ y is syntactic sugar for `(⧺)`(x)(y)
  implicit class Syntax[A](xs: List[A]) { def ⧺(ys: List[A]): List[A] = `(⧺)`(xs)(ys) }
  assert( (List(1, 2, 3) ⧺ List()) == List(1, 2, 3) )
  assert( (List(1, 2, 3) ⧺ List(1, 2, 3)) == List(1, 2, 3, 1, 2, 3) )

  ///////////////////////////////////////////////////////////////////
  // 𝑐𝑜𝑛𝑐𝑎𝑡 ∷  [[𝛼]] → [𝛼]
  // 𝑐𝑜𝑛𝑐𝑎𝑡 [] = []
  // 𝑐𝑜𝑛𝑐𝑎𝑡 (𝑥𝑠:𝑥𝑠𝑠) = 𝑥𝑠 ⧺ 𝑐𝑜𝑛𝑐𝑎𝑡 𝑥𝑠𝑠
  def concat[A]: List[List[A]] => List[A] = {
    case Nil => Nil
    case xs :: xss => xs ⧺ concat(xss)
  }
  assert( concat(List()) == List() )
  assert( concat(List(List(1, 2), Nil, List(3, 2, 1))) == List(1, 2, 3, 2, 1) )

  ///////////////////////////////////////////////////////////////////
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 ∷  [𝛼] → [𝛼]
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 [] = []
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 (𝑥:𝑥𝑠) = 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 𝑥𝑠 ⧺ [𝑥]
  def reverse[A]: List[A] => List[A] = {
    case Nil     => Nil
    case x :: xs => reverse(xs) ⧺ List(x)
  }
  assert( reverse[Int](List()) == List() )
  assert( reverse[Int](List(1, 2, 3)) == List(3, 2, 1) )
  // The above definition is not very efficient: on  a list of length
  // 𝑛, it will need a number of reduction steps proportional to 𝑛↑2 to
  // deliver the reversed list. The first element will be appended to
  // the end of a list of length (𝑛 −1), which will take about (𝑛 −1)
  // steps, the second element will be appended to a list of length
  // (𝑛 −2), taking (𝑛 −2)  steps, and so on. The total time is
  // therefore about (𝑛 − 1) + (𝑛 − 2) + … 1 = 𝑛 (𝑛 − 1)/2  steps

  ///////////////////////////////////////////////////////////////////
  // 𝑚𝑎𝑝 ∷  (𝛼 → 𝛽) → [𝛼] → [𝛽]
  // 𝑚𝑎𝑝 𝑓 [] = []
  // 𝑚𝑎𝑝 𝑓 (𝑥:𝑥𝑠) = 𝑓 𝑥 : 𝑚𝑎𝑝 𝑓 𝑥𝑠
  def map[A,B]: (A => B) => List[A] => List[B] = f => {
    case Nil     => Nil
    case x :: xs => f(x) :: map(f)(xs)
  }
  val double: Int => Int = x => 2 * x
  assert( map(double)(List()) == List() )
  assert( map(double)(List(1, 2, 3)) == List(2, 4, 6) )

  ///////////////////////////////////////////////////////////////////
  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 ∷  [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 [] = 0
  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 𝑥𝑠 = 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 (𝑖𝑛𝑖𝑡 𝑥𝑠) * 10 + (𝑙𝑎𝑠𝑡 𝑥𝑠)
  val decimal: List[Int] => Int = {
    case Nil => 0
    case xs  => decimal(xs.init) * 10 + xs.last
  }
  assert( decimal(List()) == 0 )
  assert( decimal(List(3, 4, 5)) == 345 )

  /////////////////////////////
  // Definitions using foldr //
  /////////////////////////////

  ///////////////////////////////////////////////////////////////////
  // 𝑓𝑜𝑙𝑑𝑟 ∷ (𝛼 → 𝛽 → 𝛽) → 𝛽 → [𝛼] → 𝛽
  // 𝑓𝑜𝑙𝑑𝑟 𝑓 𝑒 [] = 𝑒
  // 𝑓𝑜𝑙𝑑𝑟 𝑓 𝑒 (𝑥:𝑥𝑠) = 𝑓 𝑥 (𝑓𝑜𝑙𝑑𝑟 𝑓 𝑒 𝑥𝑠)
  // 𝑖.𝑒. 𝑓𝑜𝑙𝑑𝑟 (⊕) 𝑒 [𝑥1,𝑥2,𝑥3] = 𝑥1 ⊕ (𝑥2 ⊕ (𝑥3 ⊕ 𝑒))
  def foldr[A,B](f: A => B => B)(v: B)(s: List[A]): B = s match {
    case Nil   => v
    case x::xs => f(x)(foldr(f)(v)(xs))
  }

  ///////////////////////////////////////////////////////////////////
  // 𝑠𝑢𝑚 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑠𝑢𝑚 = 𝑓𝑜𝑙𝑑𝑟 (+) 0
  { val sum: List[Int] => Int = foldr(plus)(0)
    assert(sum(List()) == 0 )
    assert(sum(List(1, 2, 3)) == 6) }

  def plus: Int => Int => Int = m => n => m + n

  ///////////////////////////////////////////////////////////////////
  // 𝑝𝑟𝑜𝑑 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑝𝑟𝑜𝑑 = 𝑓𝑜𝑙𝑑𝑟 (×) 1
  { val prod: List[Int] => Int = foldr(mult)(1)
    assert(prod(List(2, 3, 4)) == 24) }

  def mult: Int => Int => Int = m => n => m * n

  ///////////////////////////////////////////////////////////////////
  // 𝑙𝑒𝑛𝑔𝑡ℎ ∷ [𝛼] → 𝐼𝑛𝑡
  // 𝑙𝑒𝑛𝑔𝑡ℎ = 𝑓𝑜𝑙𝑑𝑟 𝑜𝑛𝑒𝑝𝑙𝑢𝑠 0
  { def length[A]: List[A] => Int = foldr(oneplus)(0)
    assert(length(List()) == 0)
    assert(length[Int](List(1, 2, 3)) == 3) }

  def oneplus[A]: A => Int => Int = x => n => 1 + n

  ///////////////////////////////////////////////////////////////////
  // (⧺) ∷ [𝛼] → [𝛼] → [𝛼]
  // (⧺ 𝑦𝑠) = 𝑓𝑜𝑙𝑑𝑟 (:) 𝑦𝑠
  { def `(⧺)`[A]: List[A] => List[A] => List[A] = xs => ys => foldr(cons[A])(ys)(xs)
    def cons[A]: A => List[A] => List[A] = x => xs => x :: xs
    assert( (List(1, 2, 3) ⧺ List()) == List(1, 2, 3) )
    assert( (List(1,2,3) ⧺ List(1, 2, 3)) == List(1, 2, 3, 1, 2, 3) ) }

  ///////////////////////////////////////////////////////////////////
  // 𝑐𝑜𝑛𝑐𝑎𝑡 ∷ [[𝛼]] → [𝛼]
  // 𝑐𝑜𝑛𝑐𝑎𝑡 = 𝑓𝑜𝑙𝑑𝑟 (⧺) []
  // If the argument to concat is a list of length m consisting
  // lists each of length n the time complexity is O(m x n)
  { def concat[A]: List[List[A]] => List[A] = foldr(`(⧺)`[A])(Nil)
    assert( concat(List()) == List() )
    assert( concat(List(List(1, 2), Nil, List(3, 2, 1))) == List(1, 2, 3, 2, 1) )
  }

  ///////////////////////////////////////////////////////////////////
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 takes time proportional to 𝑛↑2 on a list of length 𝑛
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 ∷ [𝛼] → [𝛼]
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 = 𝑓𝑜𝑙𝑑𝑟 𝑠𝑛𝑜𝑐 []
  //         𝑤ℎ𝑒𝑟𝑒 𝑠𝑛𝑜𝑐 𝑥 𝑥𝑠 = 𝑥𝑠 ⧺ [𝑥]
  // e.g. 𝑓𝑜𝑙𝑑𝑟 𝑠𝑛𝑜𝑐 [] [𝑥1,𝑥2,𝑥3] = 𝑠𝑛𝑜𝑐 𝑥1 (𝑠𝑛𝑜𝑐 𝑥2 (𝑠𝑛𝑜𝑐 𝑥3 ⊕ [])) = [𝑥3,𝑥2,𝑥1]
  { def reverse[A](xs: List[A]): List[A] = foldr(snoc[A])(Nil)(xs)
    assert( reverse[Int](List()) == List() )
    assert( reverse[Int](List(1, 2, 3)) == List(3,2,1)) }

  def snoc[A]: A => List[A] => List[A] = x => xs => xs ⧺ List(x)

  // 𝑚𝑎𝑝 ∷ (𝛼 → 𝛽) → [𝛼] → [𝛽]
  // 𝑚𝑎𝑝 𝑓 = 𝑓𝑜𝑙𝑑𝑟 (𝑐𝑜𝑛𝑠∙𝑓) []
  //         𝑤ℎ𝑒𝑟𝑒 𝑐𝑜𝑛𝑠 𝑥 𝑥𝑠 = 𝑥 : 𝑥𝑠
  { def map[A,B]: (A => B) => List[A] => List[B] = f => foldr(cons compose f)(Nil)_
    def cons[A]: A => List[A] => List[A] = x => xs => x :: xs
    assert( map(double)(List()) == List() )
    assert( map(double)(List(1, 2, 3)) == List(2, 4, 6) )}

  ///////////////////////////////////////////////////////////////////
  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 = 𝑓𝑜𝑙𝑑𝑟 (⊕) 0
  { def decimal: List[Int] => Int = xs => foldr(`(⊕)`)(0)(xs.reverse)
    assert( decimal(List()) == 0 )
    assert( decimal(List(3, 4, 5)) == 345 ) }

  def `(⊕)`: Int => Int => Int = x => n => 10 * n + x

  /////////////////////////////
  // Definitions using foldl //
  /////////////////////////////

  // 𝑓𝑜𝑙𝑑𝑙 ∷ (𝛽 → 𝛼 → 𝛽) → 𝛽 → [𝛼] → 𝛽
  // 𝑓𝑜𝑙𝑑𝑙 𝑓 𝑒 [] = 𝑒
  // 𝑓𝑜𝑙𝑑𝑙 𝑓 𝑒 (𝑥:𝑥𝑠) = 𝑓𝑜𝑙𝑑𝑙 𝑓 (𝑓 𝑒 𝑥) 𝑥𝑠
  // 𝑖.𝑒. 𝑓𝑜𝑙𝑑𝑙 (⊕) 𝑒 [𝑥1,𝑥2,𝑥3] = ((𝑒 ⊕ 𝑥1) ⊕ 𝑥2) ⊕ 𝑥3
  @tailrec
  def foldl[A,B](f: B => A => B)(e: B)(s: List[A]): B = s match {
    case Nil   => e
    case x::xs => foldl(f)(f(e)(x))(xs)
  }

  ///////////////////////////////////////////////////////////////////
  // 𝑠𝑢𝑚 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑠𝑢𝑚 = 𝑓𝑜𝑙𝑑𝑙 (+) 0
  { val sum: List[Int] => Int = foldl(plus)(0)
    assert(sum(List()) == 0)
    assert(sum(List(1, 2, 3)) == 6) }

  ///////////////////////////////////////////////////////////////////
  // 𝑝𝑟𝑜𝑑 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑝𝑟𝑜𝑑 = 𝑓𝑜𝑙𝑑𝑙 (×) 1
  { val prod: List[Int] => Int = foldl(mult)(1)
    assert(prod(List(2, 3, 4)) == 24) }

  ///////////////////////////////////////////////////////////////////
  // 𝑙𝑒𝑛𝑔𝑡ℎ ∷ [𝛼] → 𝐼𝑛𝑡
  // 𝑙𝑒𝑛𝑔𝑡ℎ = 𝑓𝑜𝑙𝑑𝑙 𝑝𝑙𝑢𝑠𝑜𝑛𝑒 0
  { def length[A]: List[A] => Int = foldl(plusOne)(0)
    assert(length(List()) == 0)
    assert(length[Int](List(1, 2, 3)) == 3) }

  def plusOne[A]: Int => A => Int = n => x => n + 1

  ///////////////////////////////////////////////////////////////////
  // 𝑐𝑜𝑛𝑐𝑎𝑡 ∷ [[𝛼]] → [𝛼]
  // 𝑐𝑜𝑛𝑐𝑎𝑡 = 𝑓𝑜𝑙𝑑𝑙 (⧺) []
  // The time complexity is O(m↑2 x n) i.e. using foldr rather
  // than foldl results in an asymptotically faster program
  { def concat[A]: List[List[A]] => List[A] = foldl(`(⧺)`[A])(Nil)
    assert( concat(List()) == List() )
    assert( concat(List(List(1, 2), Nil, List(3, 2, 1))) == List(1, 2, 3, 2, 1) ) }

  ///////////////////////////////////////////////////////////////////
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 takes time proportional to 𝑛 on a list of length 𝑛
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 ∷ [𝛼] → [𝛼]
  // 𝑟𝑒𝑣𝑒𝑟𝑠𝑒 = 𝑓𝑜𝑙𝑑𝑙 𝑐𝑜𝑛𝑠 []
  //         𝑤ℎ𝑒𝑟𝑒 𝑐𝑜𝑛𝑠 𝑥𝑠 𝑥 = 𝑥 : 𝑥𝑠
  // e.g. 𝑓𝑜𝑙𝑑𝑙 𝑐𝑜𝑛𝑠 [] [𝑥1,𝑥2,𝑥3] = 𝑐𝑜𝑛𝑠 (𝑐𝑜𝑛𝑠 (𝑐𝑜𝑛𝑠 [] 𝑥1) 𝑥2) 𝑥3 = [𝑥3,𝑥2,𝑥1]
  { def reverse[A](xs: List[A]): List[A] = foldl(cons[A])(Nil)(xs)
    assert( reverse[Int](List()) == List() )
    assert( reverse[Int](List(1, 2, 3)) == List(3,2,1) ) }

  def cons[A]: List[A] => A => List[A] = xs => x => x::xs

  ///////////////////////////////////////////////////////////////////
  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 ∷ [𝐼𝑛𝑡] → 𝐼𝑛𝑡
  // 𝑑𝑒𝑐𝑖𝑚𝑎𝑙 = 𝑓𝑜𝑙𝑑𝑙 (⊕) 0
  { def decimal: List[Int] => Int = foldl(`(⊕)`)(0)_
    def `(⊕)`: Int => Int => Int = n => x => 10 * n + x
    assert( decimal(List()) == 0 )
    assert( decimal(List(3, 4, 5)) == 345 ) }

  //////////////////////
  // Duality Theorems //
  //////////////////////

  // First duality theorem. Suppose (⊕) is associative with unit 𝑒. Then
  //   𝑓𝑜𝑙𝑑𝑟 (⊕) 𝑒 𝑥𝑠 = 𝑓𝑜𝑙𝑑𝑙 (⊕) 𝑒 𝑥𝑠
  val sumr = foldr(plus)(0)_
  val suml = foldl(plus)(0)_
  val list: List[Int] = List(1, 2, 3)
  assert( sumr(list) == suml(list) )
  assert( foldr(plus)(0)(list) == foldl(plus)(0)(list) )

  // Second duality theorem. This is a generalization of the first.
  // Suppose ⊕, ⊗, and 𝑒 are such that for all 𝑥, 𝑦, and 𝑧 we have
  //   𝑥 ⊕ (𝑦 ⊗ 𝑧)  = (𝑥 ⊕ 𝑦) ⊗ 𝑧
  //   𝑥 ⊕ 𝑒 = 𝑒 ⊗ 𝑥
  // in other words, ⊕ and ⊗ associate with each other, and 𝑒 on the
  // right of ⊕ is equivalent to 𝑒 on the left of ⊗. Then
  //   𝑓𝑜𝑙𝑑𝑟 (⊕) 𝑒 𝑥𝑠 = 𝑓𝑜𝑙𝑑𝑙 (⊗) 𝑒 𝑥𝑠
  val lengthr = foldr(oneplus)(0)_
  val lengthl = foldl(plusOne)(0)_
  assert( lengthr(list) == lengthl(list) )
  assert( foldr(oneplus)(0)(list) == foldl(plusOne)(0)(list))
  val reverser = foldr(snoc[Int])(Nil)_
  val reversel = foldl(cons[Int])(Nil)_
  assert( reverser(list) == reversel(list) )
  assert( foldr(snoc[Int])(Nil)(list) == foldl(cons[Int])(Nil)(list))

  // Third duality theorem. For all finite lists 𝑥𝑠,
  // 𝑓𝑜𝑙𝑑𝑟 𝑓 𝑒 𝑥𝑠 = 𝑓𝑜𝑙𝑑𝑙 (𝑓𝑙𝑖𝑝 𝑓) 𝑒 (𝑟𝑒𝑣𝑒𝑟𝑠𝑒 𝑥𝑠)
  //              𝑤ℎ𝑒𝑟𝑒 𝑓𝑙𝑖𝑝 𝑓 𝑥 𝑦 = 𝑓 𝑦 𝑥
  { def flip[A, B, C]: (A => B => C) => (B => A => C) = f => b => a => f(a)(b)

    val sumr = foldr(plus)(0)_
    val suml = (xs: List[Int]) => foldl(flip(plus))(0)(xs.reverse)
    assert( sumr(list) == suml(list) )
    assert( foldr(plus)(0)(list) == foldl(flip(plus))(0)(list.reverse) )

    val lengthr = foldr(oneplus)(0)_
    def lengthl[A] = (xs: List[A]) => foldl(flip(oneplus))(0)(xs.reverse)
    assert( lengthr(list) == lengthl(list) )
    assert( foldr(oneplus)(0)(list) == foldl(flip(oneplus))(0)(list.reverse) )

    val decimalr = foldr(`(⊕)`)(0)_
    val decimall = (xs: List[Int]) => foldl(flip(`(⊕)`))(0)(xs.reverse)
    assert( decimalr(list) == decimall(list) )
    assert( foldr(`(⊕)`)(0)(list) == foldl(flip(`(⊕)`))(0)(list.reverse) ) }

}

