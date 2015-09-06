package verify.ch1

/**
 * http://book.impress.co.jp/books/1114101091
 * <p>Part1. 関数型プログラミングの基礎より</p>
 *
 * @author forrestinc
 * @since 2015/08/31
 */
object Cafe {

  /* 一杯注文する */
  def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
    val cup = new Coffee()
    (cup, Charge(cc, cup.price))
  }

  /* 請求 */
  case class Charge(cc: CreditCard, amount: Double) {

    /* 請求金額をまとめる */
    def combine(other: Charge): Charge =
      if (cc == other.cc)
        Charge(cc, amount + other.amount)
      else
        throw new Exception("カード請求をまとめることができません。")
  }

  /* コーヒー */
  case class Coffee() {
    val price = 220 // 一杯220円
  }

  /* クレジットカード */
  case class CreditCard(/* card brand */ brand: String)

  /* まとめて注文する */
  def buyCoffees(cc: CreditCard, n: Int): (List[Coffee], Charge) = {
    val purchases: List[(Coffee, Charge)] = List.fill(n)(buyCoffee(cc))
    val (coffees, charges) = purchases.unzip
    (coffees, charges.reduce((c1, c2) => c1.combine(c2)))
  }

  /* クレジットカード毎に請求金額を集計する */
  def coalesce(charges: List[Charge]): List[Charge] =
    charges.groupBy(_.cc).values.map(_.reduce(_ combine _)).toList


  /*@debug*/
  def main(args: Array[String]) {
    if (args.size == 0) {

      val cc1 = new CreditCard("master") // 同
      val cc3 = new CreditCard("master") // 同
      val cc2 = new CreditCard("visa")

      /* 団体 Master7杯注文 */
      val cups = buyCoffees(cc1, 7)
      val chargeLarge = cups._2 // 団体の請求金額をまとめているChargeを抽出

      /* 個人 Visa x 2名 ＋ Master x 1名　追加 */
      val chargeList = chargeLarge :: List(Charge(cc2, 220), Charge(cc2, 660), Charge(cc3, 220))

      /*  カード別に集計する */
      println(coalesce(chargeList))

    } else {
      printf("Invalid args.")
    }
  }
}
