import collection.mutable.Stack
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class UserDalTest extends FlatSpec with ShouldMatchers  {

  "A UserDal" should "return a proper user whan you pass email" in {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    stack.pop() should equal (2)
    stack.pop() should equal (1)
  }

  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new Stack[String]
    evaluating { emptyStack.pop() } should produce [NoSuchElementException]
  }
}
