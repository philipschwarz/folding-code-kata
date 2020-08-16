
# Folding Code Kata

## Based on ['Folding Unfolded - Polyglot FP for Fun and Profit - Haskell and Scala - Part 1'](https://www.slideshare.net/pjschwarz/folding-unfolded-polyglot-fp-for-fun-and-profit-haskell-and-scala)

1. Implement **foldr** (right fold) and **foldl** (left fold) - (optional - best to skip this initially and maybe come back to it later)
   * [Fold Problem](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_00_Fold_Problem.scala)
   * [Fold Solution](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_00_Fold_Solution.scala)
2. Practice implementing a number of simple functions using recursion, foldr and foldl. For each function, e.g. length, there is a problem Scala file where you can implement the function by filling in the code marked by ???, and a solution Scala file in which you can see a ready-made implementation. Both files contain assertions that get executed when you run the App contained in the file. 
   * [Length Problem](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_01_Length_Problem.scala)
   * [Length Solution](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_01_Length_Solution.scala)
   * [Sum Problem](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_02_Sum_Problem.scala)
   * [Sum Solution](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_02_Sum_Solution.scala)
   * [Concatenate Problem](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_03_Concatenate_Problem.scala)
   * [Concatenate Solution](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_03_Concatenate_Solution.scala)
   * [Concat Problem](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_04_Concat_Problem.scala)
   * [Concat Solution](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_04_Concat_Solution.scala)
   * [Reverse Problem](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_05_Reverse_Problem.scala)
   * [Reverse Solution](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_05_Reverse_Solution.scala)
   * [Map Problem](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_06_Map_Problem.scala)
   * [Map Solution](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_06_Map_Solution.scala)
   * [Decimal Problem](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_07_Decimal_Problem.scala)
   * [Decimal Solution](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_07_Decimal_Solution.scala)
3. See examples of the three fold duality theorems in action:                  
   * [First Duality Theorem](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_08_First_Duality_Theorem.scala)
      * foldr (⊕) e xs = foldl (⊕) e xs 
      * for all finite lists xs
      * if x ⊕ (y ⊕ z)  = (x ⊕ y) ⊕ z 
      * and x ⊕ e = e ⊕ x
   * [Second Duality Theorem](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_09_Second_Duality_Theorem.scala)
      * foldr (⊕) e xs = foldl (⊗) e xs 
      * for all finite lists xs
      * if x ⊕ (y ⊗ z)  = (x ⊕ y) ⊗ z 
      * and x ⊕ e = e ⊗ x
   * [Third Duality Theorem](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_10_Third_Duality_Theorem.scala) 
      * foldr f e xs = foldl (flip f) e (reverse xs) 
      * where flip f x y = f y x                 
 
4. See all solutions and theorem examples in a single file

   * [All Solutions and Theorem Examples](https://github.com/philipschwarz/folding-code-kata-scala/blob/master/src/main/scala/_11_All_Solutions_and_Theorem_Examples.scala)
    
## Sample file contents
### Problem File Example: _01_Length_Problem.scala
 
 ```Scala
import _00_Fold_Solution.{foldl, foldr}

object _1_Length extends App {

  // length ∷ [α] → Int
  // length [] = ???
  // length (x:xs) = ???   
  def length[A]: List[A] => Int = {
    case Nil     => ???
    case x :: xs => ???
  }
  assert(length(List(1, 2, 3)) == 3)
  
  // length ∷ [α] → Int
  // length = foldr ??? ???
  { def length[A]: List[A] => Int = foldr(???)(???)
    assert(length[Int](List(1, 2, 3)) == 3) }
  
  def oneplus[A]: A => Int => Int =
    x => n => ???
  
  // length ∷ [α] → Int
  // length = foldl ??? ???
  { def length[A]: List[A] => Int = foldl(???)(???)
    assert(length[Int](List(1, 2, 3)) == 3) }
  
  def plusOne[A]: Int => A => Int =
    n => x => ???
}
 ```

### Solution File Example: _01_Length_Solution.scala

```Scala
import _00_Fold_Solution.{foldr,foldl}

object _1_Length_Solution extends App {

  // length ∷ [α] → Int
  // length [] = 0
  // length (x:xs) = 1 + length xs
  def length[A]: List[A] => Int = {
    case Nil     => 0
    case x :: xs => 1 + length(xs)
  }
  assert(length(List(1, 2, 3)) == 3)

  // length ∷ [α] → Int
  // length = foldr oneplus 0
  { def length[A]: List[A] => Int = foldr(oneplus)(0)
    assert(length[Int](List(1, 2, 3)) == 3) }

  def oneplus[A]: A => Int => Int =
    x => n => 1 + n

  // length ∷ [α] → Int
  // length = foldl plusone 0
  { def length[A]: List[A] => Int = foldl(plusOne)(0)
    assert(length[Int](List(1, 2, 3)) == 3) }

  def plusOne[A]: Int => A => Int =
    n => x => n + 1
}
```