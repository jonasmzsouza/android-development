fun main() {
    // Collections
	
    // Array
    // Creating an Empty String Array
    var emptyArray =  arrayOf<String>()
    // Creating an Array of Strings and Feeding Values on Creation
    var shoppingList = arrayOf<String>("Milk", "Bread", "Butter", "Sugar")
    // Using inference
    var inferredShoppingList = arrayOf("Milk", "Bread", "Butter", "Sugar")
    //Testando se um Array está vazio
    if (shoppingList.isEmpty()) {
      println("The shopping list is empty")
    } else {
      println("The shopping list is NOT empty")
    }
    // Retrieving the Total Array Elements
    println("Our shopping list has ${shoppingList.size} items")
    // Listing all items
    println(shoppingList[0]) 
    println(shoppingList[1]) 
    println(shoppingList[2]) 
    println(shoppingList[3])
    
    
    // List
    // Creating a List of Strings
    var movies = ArrayList<String> ()
      movies.addAll(listOf(
        "Matrix",
        "Avengers",
        "Jurassic Park",
        "Back to the Future"
      ))
    // Creating an empty list
    var movies2 = ArrayList<String> ()
    // inserting elements
    movies.add("Spider-Man: No Way Home")
    println(movies.count())
	// List accepts repeated items 
    movies.add("Spider-Man: No Way Home")
    println(movies) 
    println(movies.count())
    // Removing 2 repeated elements
    movies.remove("Spider-Man: No Way Home")
    movies.remove("Spider-Man: No Way Home")
    println(movies) 
    // Scrolling through a List
    for (movie in movies) {
      println(movie)
    }
    // Checking if a certain element is contained in the List
    if (movies.contains("Matrix")) {
      println("Matrix is on my list of favorite movies!!")
    } 
    // Create a new simplified List
    var myWifeMovies  = listOf(
      "Looping",
      "John Wick",
      "Resident Evil",
      "Back to the Future",
      "Jurassic Park"
    )
    // Creating a List with All Movies
    var allMovies = movies + myWifeMovies
    println(allMovies)
}