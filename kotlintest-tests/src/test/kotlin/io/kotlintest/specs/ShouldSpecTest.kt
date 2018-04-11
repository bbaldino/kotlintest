package io.kotlintest.specs

import io.kotlintest.Description
import io.kotlintest.ListStack
import io.kotlintest.matchers.gt
import io.kotlintest.matchers.lt
import io.kotlintest.shouldBe

class ShouldSpecTest : AbstractShouldSpec() {
  private var num = -1

  override fun beforeTest(description: Description) {
    super.beforeTest(description)
  }

  init {
    // My tests
//    "When you start with 0" {
//      println("Adding 'setting num to 0' context")
//      withContext {
//        println("setting num to 0")
//        num = 0
//      }
//      "and add 10" {
//        withContext {
//          println("adding 10")
//          num += 10
//        }
//        "and then subtract 5" {
//            withContext {
//              println("subtracting 5")
//                num -= 5
//            }
//            should("be equal to 5") {
//              println("testing for 5")
//                num shouldBe 5
//            }
//        }
//        should("be equal to 10") {
//          num shouldBe 10
//        }
//        "and then add 5" {
//          withContext {
//            num += 5
//          }
//          should("be equal to 15") {
//            num shouldBe 15
//          }
//        }
//
//      }
//
//    }





    // should allow multi nested
    "List" {
      "pop" {
        should("remove the last element from stack") {
          val stack = ListStack<String>()
          stack.push("hello")
          stack.push("world")
          stack.size() shouldBe 2
          stack.pop() shouldBe "world"
          stack.size() shouldBe 1
        }
      }
      should("remove the last element from stack") {
        val stack = ListStack<String>()
        stack.push("hello")
        stack.push("world")
        stack.size() shouldBe 2
        stack.pop() shouldBe "world"
        stack.size() shouldBe 1
      }
    }

    // should allow nested
    "List.pop" {
      should("remove the last element from stack") {
        val stack = ListStack<String>()
        stack.push("hello")
        stack.push("world")
        stack.size() shouldBe 2
        stack.pop() shouldBe "world"
        stack.size() shouldBe 1
      }
    }

    // and un-nested
    should("leave the stack unmodified") {
      val stack = ListStack<String>()
      stack.push("hello")
      stack.push("world")
      stack.size() shouldBe 2
      stack.peek() shouldBe "world"
      stack.size() shouldBe 2
    }

    should("support config") {
    }.config(invocations = 5)
  }
}
