fun main() {
    // Set
    // Creating a String Set
    var movies = HashSet<String> ()
    var catalog = listOf(
      "Matrix",
      "Avengers",
      "Jurassic Park",
      "Back to the Future"
    )
    movies.addAll(catalog)
    // Note that the movie catalog is sorted alphabetically
    println(movies)
    println(movies.count())
    // Creating an empty set
    var movies2 = HashSet<String> ()
    // Inserting elements
    movies.add("Spider-Man: No Way Home")
    println(movies)
    println(movies.count())
	// Set DOES NOT accept repeated items.
    movies.add("Spider-Man: No Way Home")
    println(movies)
    println(movies.count())
    // Removing element
    movies.remove("Spider-Man: No Way Home")
    println(movies)
    println(movies.count())
    // Scrolling through a Set
    for (movie in movies) {
      println(movie)
    }
    //Checking if a certain element is contained in the Set
    if (movies.contains("Matrix")) {
      println("Matrix is on my list of favorite movies!!")
    }
    // Create a new simplified set
    var myWifeMovies  = setOf(
      "Looping",
      "John Wick",
      "Resident Evil",
      "Back to the Future",
      "Jurassic Park"
    )
    // Creating a Set with all the movies.
    var allMovies = movies + myWifeMovies
    println(allMovies)
    println(allMovies.count())
}