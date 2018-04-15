package io.kotlintest.specs

import io.kotlintest.Description
import io.kotlintest.ListStack
import io.kotlintest.Spec
import io.kotlintest.matchers.gt
import io.kotlintest.matchers.lt
import io.kotlintest.shouldBe

class ShouldSpecTest : AbstractShouldSpec() {
  private var num = -1

  override fun beforeSpec(description: Description, spec: Spec) {
    super.beforeSpec(description, spec)
    println("======>MY TEST")
  }

  override fun beforeTest(description: Description) {
    super.beforeTest(description)
  }

  init {
    // My tests
    println("declaring top level context in test")
    "When you start with 0" {
      println("Adding 'setting num to 0' context")
//      withContext("setting num to 0") {
//        println("WC: setting num to 0")
//        num = 0
//      }
      withContext(WithContext("setting num to 0") {
        println("WC: setting num to 0")
        num = 0
      })
      "and add 10" {
//        withContext("adding 10") {
//          println("WC: adding 10")
//          num += 10
//        }
        withContext(WithContext("adding 10") {
          println("WC: adding 10")
          num += 10
        })
        "and then subtract 5" {
//            withContext("subtracting 5") {
//              println("WC: subtracting 5")
//                num -= 5
//            }
          withContext(WithContext("subtracting 5") {
            println("WC: subtracing 5")
            num -= 5
          })
            should("be equal to 5") {
              println("testing for 5")
              num shouldBe 5
            }
        }
        should("be equal to 10") {
          num shouldBe 10
        }
        "and then add 5" {
//          withContext("adding 5") {
//            println("WC: adding 5")
//            num += 5
//          }
          withContext(WithContext("adding 5") {
            println("WC: adding 5")
            num += 5
          })
          should("be equal to 15") {
            num shouldBe 15
          }
        }
      }

    }

//    println("Test Init")
//    var count = 0
//    "when count is increased by 10" {
//      count += 10
//      should("be 10") {
//        count shouldBe 10
//      }
//    }
//    should("be 0") {
//      count shouldBe 0
//      count = 100
//    }
//    "when count is increased by 20" {
//      count += 10
//      should("be 20") {
//        count shouldBe 20
//      }
//    }
//    should("be 0 part 2") {
//      count shouldBe 0
//      count = 100
//    }
//    should("be 0 part 3") {
//      count shouldBe 0
//      count = 100
//    }
//    should("still be 0") {
//      count shouldBe 0
//    }



    // should allow multi nested
//    "List" {
//      "pop" {
//        should("remove the last element from stack") {
//          val stack = ListStack<String>()
//          stack.push("hello")
//          stack.push("world")
//          stack.size() shouldBe 2
//          stack.pop() shouldBe "world"
//          stack.size() shouldBe 1
//        }
//      }
//      should("remove the last element from stack") {
//        val stack = ListStack<String>()
//        stack.push("hello")
//        stack.push("world")
//        stack.size() shouldBe 2
//        stack.pop() shouldBe "world"
//        stack.size() shouldBe 1
//      }
//    }
//
//    // should allow nested
//    "List.pop" {
//      should("remove the last element from stack") {
//        val stack = ListStack<String>()
//        stack.push("hello")
//        stack.push("world")
//        stack.size() shouldBe 2
//        stack.pop() shouldBe "world"
//        stack.size() shouldBe 1
//      }
//    }
//
//    // and un-nested
//    should("leave the stack unmodified") {
//      val stack = ListStack<String>()
//      stack.push("hello")
//      stack.push("world")
//      stack.size() shouldBe 2
//      stack.peek() shouldBe "world"
//      stack.size() shouldBe 2
//    }
//
//    should("support config") {
//    }.config(invocations = 5)
  }
}
