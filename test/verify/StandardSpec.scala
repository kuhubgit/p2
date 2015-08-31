package verify

import java.io.{BufferedOutputStream, ByteArrayOutputStream, PrintStream}

import org.scalatest._

/**
 * ScalaTestライブラリの検証
 */
class StandardSpec extends FlatSpec with Matchers with DiagrammedAssertions {

    "Predef.print" should "print hello" in {
        val outStream = new ByteArrayOutputStream
        val out = new PrintStream(new BufferedOutputStream(outStream), true, "utf-8")
        Console.withOut(out) {
            print("hello")
            out.flush()
            outStream.toString("utf-8") should equal("hello")
        }
    }

}
