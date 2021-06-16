fun main() {
    // Types
    // Integer Types (Long, int, short and byte)
    // Way to show the maximum value accepted by the type
    println(Int.MAX_VALUE)
    // Way to show the minimum value accepted by the type
    println(Int.MIN_VALUE)
    
    // Double and float (numbers with decimal places)
    // Type Double is the default type for numeric values with decimal places.
    var balance = 1750.75           // Double automatically inferred
    var sallary: Double = 1416.50   // explicit double
    // To use Float, we need to add the letter F at the end of the information.
    var height = 1.46f
    var temperature: Float = 84.9F
    // Presenting the information
    println(balance)
    println(sallary)
    println(height)
    println(temperature)
    
    // String and Char
    var module: String = "Introduction to Kotlin"
    var schoolName = "FIAP"
    // Note that letter in the line below is a String due to type inference
    var letter = "A"
    // To use Char, we need to explicitly define
    var gender: Char = 'M'
    // Apresentando os valores
    println(module)
    println(schoolName)
    println(letter)
    // To display Char values, conversion to String is required.
    // Use the $ character for output.
    println("$gender")
    
    // Bool
    var isApproved = true
	var firstTime: Boolean = false
    
    // Pair
    val (address,number) = Pair("Av. Lins de Vasconcelos", 1264)
    println(address)
    println(number)
    
    // Null safety
    // For a variable to contain a null value, it is necessary to use the suffix ? in type
    var driverLicense: String? = null   
    if (driverLicense != null) {
      println("The driver's license is $driverLicense")
    } else {
      println("This person does not have a driver's license")
    }
}