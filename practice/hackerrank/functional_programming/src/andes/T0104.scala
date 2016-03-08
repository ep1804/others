package andes

object T0104 {

  def check_IP(s: Array[String]): Array[String] = {

    import scala.util.{ Try, Success, Failure }

    def ipv4(s: String): Boolean = {
      Try {
        val ss = s.trim.split("\\.")
        if (ss.length != 4) throw new Exception()
        ss foreach { s => if (s.length > 1 && s.head == '0') throw new Exception() } // case "00.0.0.0"
        val ns = ss.map(_.toInt)
        if (!ns.forall(n => n >= 0 && n <= 255)) throw new Exception()
      } match {
        case Success(_) => true
        case Failure(_) => false
      }
    }

    def ipv6(s: String): Boolean = {

      val r1 = "[a-f0-9]{1,4}:[a-f0-9]{1,4}:[a-f0-9]{1,4}:[a-f0-9]{1,4}:[a-f0-9]{1,4}:[a-f0-9]{1,4}:[a-f0-9]{1,4}:[a-f0-9]{1,4}"

      s.matches(r1)
    }

    s.map(addr => if (ipv4(addr)) "IPv4" else if (ipv6(addr)) "IPv6" else "Neither")
  }

  def main(args: Array[String]): Unit = {
    println(check_IP(Array("11.22.33.44", "00.0.0.k", "00.0.0.0", "ad00:ad00:ad00:ad00:ad00:ad00:ad00:ad00")).toList)
  }
}