
# Folding Code Kata

## Based on ['Folding Unfolded - Polyglot FP for Fun and Profit - Haskell and Scala - Part 1'](https://www.slideshare.net/pjschwarz/folding-unfolded-polyglot-fp-for-fun-and-profit-haskell-and-scala)

1. Implement foldr (right fold) and foldl (left fold) - (optional - best to skip this initially and maybe come back to it later)
   * _0_Fold_Problem
   * _0_Fold_Solution
2. Practice implementing a number of simple functions using recursion, foldr and foldl. For each function, e.g. length, there is a problem Scala file where you can implement the function by filling in the code marked by ???, and a solution Scala file in which you can see a ready-made implementation. Both files contain assertions that get executed when you run the App contained in the file. 
   * _1_Length_Problem
   * _1_Length_Solution
   * _2_Sum_Problem
   * _2_Sum_Solution
   * _3_Concatenate_Problem
   * _3_Concatenate_Solution
   * _4_Concat_Problem
   * _4_Concat_Solution
   * _5_Reverse_Problem
   * _5_Reverse_Solution
   * _6_Map_Problem
   * _6_Map_Solution
   * _7_Decimal_Problem
   * _7_Decimal_Solution   
3. See examples of the three fold duality theorems in action:                  
   * _8_First_Duality_Theorem
      * foldr (⊕) e xs = foldl (⊕) e xs 
      * for all finite lists xs
      * if x ⊕ (y ⊕ z)  = (x ⊕ y) ⊕ z 
      * and x ⊕ e = e ⊕ x
   * _9_Second_Duality_Theorem
      * foldr (⊕) e xs = foldl (⊗) e xs 
      * for all finite lists xs
      * if x ⊕ (y ⊗ z)  = (x ⊕ y) ⊗ z 
      * and x ⊕ e = e ⊗ x
   * _10_Third_Duality_Theorem 
      * foldr f e xs = foldl (flip f) e (reverse xs) 
      * where flip f x y = f y x                 
 
4. See all the code and tests in a single file

   * _11_All_In_One
    
## Sample file contents
### Problem File Example: _1_Length_Problem.scala
 
 ```Scala
import _0_Fold_Solution.{foldl, foldr}

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

### Solution File Example: _1_Length_Solution.scala

```Scala
import _0_Fold_Solution.{foldr,foldl}

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