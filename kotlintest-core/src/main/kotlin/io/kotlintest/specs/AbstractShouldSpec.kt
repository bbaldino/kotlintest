package io.kotlintest.specs

import io.kotlintest.AbstractSpec
import io.kotlintest.TestCase
import io.kotlintest.TestContainer
import io.kotlintest.TestContext
import io.kotlintest.lineNumber

/**
 * Example:
 *
 * "some test" {
 *   "with stateStack" {
 *      should("do something") {
 *        // test here
 *      }
 *    }
 *  }
 *
 *  or
 *
 *  should("do something") {
 *    // test here
 *  }
 */

data class WithContext(val name: String, val ctx: () -> Unit)

abstract class AbstractShouldSpec(body: AbstractShouldSpec.() -> Unit = {}) : AbstractSpec() {

  init {
    body()
  }

  final override fun isInstancePerTest(): Boolean = false

  private val stateStack = mutableListOf<WithContext>()

  fun should(name: String, test: TestContext.() -> Unit): TestCase {
    val tc = TestCase(rootDescription().append("should $name"), this@AbstractShouldSpec, test, lineNumber(), defaultTestCaseConfig)
    rootScopes.add(tc)
    return tc
  }

  fun beforeEach(ctx: WithContext) {
    println("AbstractShouldSpec ${this@AbstractShouldSpec} adding withcontext ${ctx.name}")
    stateStack.add(ctx)
  }

  operator fun String.invoke(init: ShouldContext.() -> Unit) {
    println("AbstractShouldSpec Invoking string from ${this@AbstractShouldSpec}, root description is: ${rootDescription().name}")
    rootScopes.add(TestContainer(rootDescription().append(this), this@AbstractShouldSpec, { ShouldContext(it, stateStack).init() }))
  }


  inner class ShouldContext(val context: TestContext, inheritedStateStack: List<WithContext> = listOf()) {

    init {
      println("Creating ShouldContext with ${inheritedStateStack.size} WithContexts:")
      inheritedStateStack.forEach { println(it) }
    }

    private val stateStack = inheritedStateStack.toMutableList()

    fun beforeEach(ctx: WithContext) {
      println("ShouldContext ${this@ShouldContext} adding withcontext ${ctx.name}")
      stateStack.add(ctx)
    }

    operator fun String.invoke(init: ShouldContext.() -> Unit) {
      context.addScope(TestContainer(context.currentScope().description().append(this), this@AbstractShouldSpec, { ShouldContext(it, stateStack).init() }))
    }


    fun should(name: String, test: TestContext.() -> Unit): TestCase {
      val testWrapper = { context: TestContext ->
        println("ShouldContext test $name has ${stateStack.size} contexts to execute")
        stateStack.forEach { it.ctx() }
        test(context)
      }
      val tc = TestCase(context.currentScope().description().append("should $name"), this@AbstractShouldSpec, testWrapper, lineNumber(), defaultTestCaseConfig)
      context.addScope(tc)
      return tc
    }
  }
}
