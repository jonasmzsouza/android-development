fun main() {
    
  var int1 = 20
  var int2 = 30

  with(int1) {
    int1 = int2
    int2 = this
  }

  println("$int1, $int2")
  
}