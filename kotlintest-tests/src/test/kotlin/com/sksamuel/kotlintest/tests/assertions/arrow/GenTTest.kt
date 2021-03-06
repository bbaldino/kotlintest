//package com.sksamuel.kotlintest.tests.assertions.arrow
//
//import arrow.core.Tuple2
//import io.kotlintest.assertions.arrow.gen.genT
//import io.kotlintest.specs.StringSpec
//
//@product
//data class Person(val name: String, val age: Int) {
//  init {
//    require(age > 0,
//        { "Age should be greater than zero" })
//  }
//
//  companion object
//}
//
//class GenTTest : StringSpec({
//
//  fun tupleToAccount(t: Tuple2<Int, Int>): Person = t.toAccount()
//
//  "GenT should infer types for tuples" {
//    genT()
//  }
//})