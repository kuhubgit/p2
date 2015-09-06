package verify.ch3

import org.scalatest.FlatSpec

import org.scalatest._

/**
 *
 * @author forrestinc
 *
 */
class TreeSpec extends FlatSpec with Matchers {

  "Tree.size" should "is available" in {
    val right = Leaf("right")
    val left = Leaf("left")
    val branch = Branch(right, left)
    val capacity  = Tree.size(branch)
    capacity should equal(3)
    println(s"Tree size = [ ${capacity} ]")
  }

  "Tree.sizeViaFold" should "is available" in {
    val right = Leaf("right")
    val left = Leaf("left")
    val branch = Branch(right, left)
    val capacity  = Tree.sizeViaFold(branch)
    capacity should equal(3)
    println(s"Tree size = [ ${capacity} ]")
  }

}
