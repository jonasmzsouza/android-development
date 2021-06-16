fun main() {
    // Creating a String Map
    var movies = HashMap<Int,String> ()
    var catalog = mapOf(
      Pair(10,"Matrix"),
      Pair(20,"Avengers"),
      Pair(30,"Jurassic Park"),
      Pair(40,"Back to the Future")
    )
    // Use the putAll method to insert the catalog
    movies.putAll(catalog)
    // Note that the movie catalog is sorted alphabetically
    println(movies)
    println(movies.count())
    // creating an empty set
    var movies2 = HashSet<String> ()
    // Inserting 1 element
    movies.put(25,"Spider-Man: No Way Home")
    println(movies)
    println(movies.count())

	// Map accepts items where the keys are NOT repeated.
    //movies.put(25,"Spider-Man: No Way Home")
    movies.put(35,"Spider-Man: No Way Home")
    println(movies)
    println(movies.count())
    // Removing element
    movies.remove(25)
    println(movies)
    println(movies.count())
    // Scrolling through a Map
    for (movie in movies) {
      println(movie)
    }
    // Checking if a certain element is contained in the Map
    if (movies.containsValue("Matrix")) {
      println("Matrix is on my list of favorite movies!!")
    }
	// Create a new simplified map
    var myWifeMovies  = mapOf(
      Pair(100,"Looping"),
      Pair(200,"John Wick"),
      Pair(300,"Resident Evil"),
      Pair(400,"Back to the Future"),
      Pair(500,"Jurassic Park")
    )
    // Creating a Map with all the movies
    var allMovies = movies + myWifeMovies
    println(allMovies)
    println(allMovies.count())
    // Reading Key and Value separately
    for(movie in allMovies) {
      println("Key => ${movie.key}")
      println("Value => ${movie.value}")
      var title = movie.value.toUpperCase()
      println("UpperCase => ${title}")
      title = movie.value.toLowerCase()
      println("LowerCase => ${title}")
    } 
    // Running a search directly on the key
    var film1 = allMovies.get(400)
    println("Title => ${film1}")
    var film2 = allMovies.get(999)
    println("Title => ${film2}")

    // Checking the feasibility of testing before printing
    var code = 1234
    var film3 = allMovies.get(code)

    if(film3.isNullOrEmpty()) {
      println("Movie with code $code not found!")
    } else {
      println("Title => ${film3}")
    }    
}