fun main() {
    
	/*
     fun functionName(parameter: Type) : ReturnType {
      //Code
        return ReturnType
     }
    */

    // Simple functions
    fun printlnHelloFormalMode() {
      println("Hello!!!!")
    }
    printlnHelloFormalMode()

    fun printlnHelloReducedMode() = println("Hello!!!! Reduced Mode!")
    printlnHelloReducedMode()

    // Function that accepts parameter
    fun say(message: String) {
      println(message)
    }
    say("Let's create functions in Kotlin")

    // Function that accepts more than one parameter and returns something
    fun sumNumbers(a: Int, b: Int) : Int {
      return a + b
    }
    var result = sumNumbers(10,15)
    println(result)
    
    
    // Example of function to calculate the first 
    // 10 numbers of the Fibonacci sequence
    // Function
    fun fibonacciSequence() {

    // Declaration of variables
    var number1 = 0
    var number2 = 1

        // Controlled loop from 1 to 10
        for (sequence in 1..10) {

          // Printing the content of variable number1
          println("$sequence -> $number1")

          // Sum of 2 variable values
          var sum = number1 + number2

          // Swap values between variables
          number1 = number2
          number2 = sum
        }
    }

    // Perform function
    fibonacciSequence()
    
    fun double(x: Int): Int = x * 2
    println(double(8))
    fun triple(x: Int) = x * 3
    println(triple(10))   
}