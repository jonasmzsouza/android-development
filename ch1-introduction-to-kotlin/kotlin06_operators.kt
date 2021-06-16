fun main() {
	// Operators
	// Assignment 
	var height: Double = 1.75
    
    // Arithmetic 
    var aArith = 12
    var bArith = 3
    var sum = aArith + bArith
    var subtract = aArith - bArith
    var multiplication = aArith * bArith
    var division = aArith / bArith
    var módulus = aArith % bArith
    var minusA = -aArith
    
    // Composite
    var aC = 2
    var bC = 3
    var newValue = 5
    newValue += aC
    newValue -= bC
    newValue *= aC
    newValue /= aC
    newValue %= bC
    newValue++
    println(newValue)    
    newValue--
    println(newValue)
    
    // Logical
    var yes = true
    var no = false
    println(yes && no)
    println(yes || no)
    println(!yes)
    
    // Comparation
    var a = 12
    var b = 3
    var c = 7
    var d = 3
    println(a > b)
    println(a < b)
    println(b >= d)
    println(a <= c) 
    println(b == d)
    println(b != d)

    // Decision
    var grade = 7.5
    var result = if (grade > 7.0) "approved" else "disapproved"
    println(result) //approved
    
    // Null coalescence (Elvis operator)
    var age: Int? = null
    var myAge = age ?: 0 //0
    println(myAge)
    age = 25
    var newAge = age ?: 0 //25
    println(newAge)
	
    //Closed Range ..
    println("Closed Range (..)")
    var numbers = 1..10
    for (number in numbers) {
      println(number)   //Prints from 1 to 10
    }
    // Half Closed Range (until)
    println("Half Closed Range (until)")
    var newNumbers = (1 until 10)
    for (number in newNumbers) {
      println(number)   //Prints from 1 to 9
    }
}