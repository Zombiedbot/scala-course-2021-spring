package karazin.scala.users.group.week9.topic

object program:

  object functors:
    
    import cats.Functor
    import cats.implicits._
  
    val list: List[Int] = 1 :: 2 :: 3 :: Nil
    assert(Functor[List].map(list)(v ⇒ v + 1) == 2 :: 3 :: 4 :: Nil)
    
    val listOption: Option[List[Int]] = Some(1 :: 2 :: 3 :: Nil)
    assert(Functor[Option].compose[List].map(listOption)(_ + 1) == Some(2 :: 3 :: 4 :: Nil))

    import cats.data.Nested
    val nested: Nested[Option, List, Int] = Nested(listOption)

    assert(nested.map(_ + 1) == Some(2 :: 3 :: 4 :: Nil))
    
  end functors 

  
end program
