package monocle.syntax

import monocle.Lens
import monocle.syntax.lens._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class LensSyntaxTest extends AnyFunSuite with Matchers {

  case class Foo(map: Map[Int, String], list: List[Int], tuple: (Boolean, String))

  val foo = Foo(
    map = Map(1 -> "One", 2 -> "Two"),
    list = List(1, 2, 3),
    tuple = (false, "hello")
  )

  val map: Lens[Foo, Map[Int, String]] =
    Lens[Foo, Map[Int, String]](_.map)((foo, newV) => foo.copy(map = newV))

  val list: Lens[Foo, List[Int]] =
    Lens[Foo, List[Int]](_.list)((foo, newV) => foo.copy(list = newV))

  val tuple: Lens[Foo, (Boolean, String)] =
    Lens[Foo, (Boolean, String)](_.tuple)((foo, newV) => foo.copy(tuple = newV))

  test("_1") {
    tuple._1.get(foo) shouldEqual foo.tuple._1
    tuple._1.get(foo) shouldEqual foo.tuple._1
  }

  test("_2") {
    tuple._2.get(foo) shouldEqual foo.tuple._2
  }

  test("at") {
    map.at(1).get(foo) shouldEqual foo.map.get(1)
  }

  test("cons") {
    list.cons.getOption(foo) shouldEqual Some((foo.list.head, foo.list.tail))
  }

  test("index") {
    map.index(1).getOption(foo) shouldEqual foo.map.get(1)
  }

  test("headOption") {
    list.headOption.getOption(foo) shouldEqual foo.list.headOption
  }

  test("second") {
    tuple.second.get(foo) shouldEqual foo.tuple._2
  }

  test("tailOption") {
    list.tailOption.getOption(foo) shouldEqual Some(foo.list.tail)
  }

}