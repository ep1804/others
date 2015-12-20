import scala.annotation.tailrec

object MatrixRotation {
  
  def rotate(mtx: Array[Array[Int]], M: Int, N: Int, R: Int): Array[Array[Int]] = {
    val res = Array.ofDim[Int](M, N)
    
    def depth(m: Int, n: Int): Int = 
      Array(M - m - 1, m, N - n - 1, n).min
    
    def perimeter(m: Int, n: Int): Int = 
      (M + N) * 2 - 4 - depth(m, n) * 8 
    
    @tailrec
    def place(m: Int, n: Int, r: Int, d: Int, v: Int): Unit = {
      if(r == 0) { res(m)(n) = v; return }
      
      val maxM = M - 1 - d;
      val maxN = N - 1 - d;
      
      if(m == d && n > d){
        if(r > n - d) place(m, d, r - (n - d), d, v)
        else place(m, n - r, 0, d, v)
      }else if(m == maxM && n < maxN){
        if(r > maxN - n) place(m, maxN, r - (maxN - n), d, v)
        else place(m, n + r, 0, d, v)
      }else if(n == d && m < maxM){
        if(r > maxM - m) place(maxM, n, r - (maxM - m), d, v)
        else place(m + r, n, 0, d, v)        
      }else{ // n == N - 1 - d && M > d
        if(r > m - d) place(d, n, r - (m - d), d, v)
        else place(m - r, n, 0, d, v)
      }      
    }
    
    for(m <- 0 until M)
      for(n <- 0 until N){
        val r = R % perimeter(m, n)
        place(m, n, r, depth(m, n), mtx(m)(n))        
      }
    
    res
  }
  
  def printMtx(m: Array[Array[Int]]): Unit = 
    for(r <- m) 
      println(r.mkString(" "))
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    
    val MNR = in.next.trim.split("\\s+").map(_.toInt).toArray
    val mtx = in.take(MNR(0)).map(x => (x.trim.split("\\s+").map(_.toInt)).toArray).toArray
    
    val rotatedMtx = rotate(mtx, MNR(0), MNR(1), MNR(2))
    
    printMtx(rotatedMtx)
  }
}