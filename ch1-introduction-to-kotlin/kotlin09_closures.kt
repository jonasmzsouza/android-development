fun main() {
    

    // elaborating a List type collection with numbers between 1 to 10
    val numbers = listOf(1,2,3,4,5,6,7,8,9,10)
    println(numbers)

    // "Filtering"(filter) only the even numbers from the numbers collection
	// the temporary variable called "it" used in the operation
    var evenNumbers = numbers.filter { it%2 == 0 }
    println("Even numbers Listing: $evenNumbers")

    // "Filtering"(filter) only the odd numbers from the numbers collection
	// the temporary variable called "it" used in the operation
    var oddNumbers = numbers.filter { it%2 != 0 }
    println("Odd Number Listing: $oddNumbers")

	// Using Map performs individual processing of each element within the collection
    var multiplyNumbers = numbers.map { it * it }
    println("Multiplication: $multiplyNumbers")

    // Performs collection processing according to the parameters sent
    var sumNumbers = numbers.reduce {
    // Captures the previous or current value (acc) and the current value (it)
    acc, it ->
    // Displays the information
    println("acc = $acc, it = $it")
    // Performs information processing
    acc + it }

    println("Sum Result: $sumNumbers")

    // MAP
    var names = arrayOf("John", "Paul", "Henry", "Anna", "Beatriz", "Carl", "Carol")
    // Map in names
    var uppercasedNames = names.map({it.toUpperCase()})
    println(uppercasedNames)

    //FILTER
    // Filter in names
    var filteredNames = names.filter({it.length < 6})
    println(filteredNames)
    
    // Using Reduce
    var transactions = arrayOf<Double>(450.0, -25.0, -58.0, -27.40, -231.72, 1360.0, -48.15, -102.0)
    var balance = transactions.reduce {
    acc,it -> println("Balance: " + String.format("%.2f", acc) + " => Next: " + String.format("%.2f", it))
    (acc + it)
    }

    println("Your balance is $ " + String.format("%.2f", balance))
  
}