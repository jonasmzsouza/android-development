fun main() {
    // Conditional and repeating structures
    // if - else - else if
    var number = 11
    if (number % 2 == 0) {
      println("it's pair")
    } else {
      println("it's odd")
    }

    var temperature = 18
    var climate = ""
    if (temperature <= 0) {
      climate = "Very cold"
    } else if (temperature < 14) {
      climate = "cold"
    } else if (temperature < 21) {
      climate = "Nice weather"
    } else if (temperature < 30) {
      climate = "A little hot"
    } else {
      climate = "Too hot"
    }
    println("Temperature:$temperature degrees Status:$climate")	
    
    // WHEN
    var numberWhen = 7
    when (number % 2) {
      0 -> 
      println("$numberWhen it's pair")
      else -> 
      println("$numberWhen it's odd")
    }
    // Example with multiple scenarios in the same case
    var letter = "z"
    when (letter) {
      "a", "e", "i", "o", "u" ->
      println("vowel")
      else ->
      println("consonant")
    }
    // Example with letter range
    when (letter) {
      in "a".."f" ->
      println("You are in the class 1")
      in "g".."l" ->
      println("You are in the class 2")
      in "m".."r" ->
      println("You are in the class 3")
      else ->
      println("You are in the class 4")
    }
    // Range of numbers
    var speed = 33
    when (speed) {
      in 0 until 20 ->
      println("First gear")
      in 20 until 40 ->
      println("Second gear")
      in 40 until 50 ->
      println("Third gear")
      in 50 until 90 ->
      println("Fourth gear")
      else ->
      println("Fifth gear")
    }
    
    // WHILE / DO WHILE
    //while
    var life = 10
    while (life > 0) {
      println("The player has $life lives")
      life = life - 1
    }  
    //Do while
    var tries = 0
    var diceNumber = 0
    do {
      tries += 1
      diceNumber = ((Math.random() * 6) + 1).toInt()
      println("Try: $tries <-> Random number: $diceNumber")
    } while (diceNumber != 6)
    println("You got 6 after $tries tries")
    
    // For in
    // Scrolling through an Array
    var students = arrayOf(
      "John Blue",
      "Paul Black",
      "Mary White",
      "Jessica Pink",
      "Peter Green"
    )   
    for (student in students) {
      println("Student $student came to class today!")
    }
    // Stepping through a sequence (range)
    for (day in 1..30) {
      println("Estou no dia $day")
    }
    // Note below that a String is also a collection.
    var name = "FIAP"
    for (letter in name) {
      println(letter)
    }
    var people = mapOf(
      (25 to "Paul"),
      (18 to "Carol"),
      (33 to "James"),
      (51 to "Robert"),
      (36 to "Jhennifer")
    )
    for (person in people) {
      println("${person.key} => ${person.value}")
    }

    // We can break the execution of a loop using the break command
    var grades = arrayOf(10.0, 9.0, 8.5, 7.0, 9.5, 5.0, 22.0, 6.5, 10.0)
    for (grade in grades) {
      println(grade)
      if (grade < 0.0 || grade > 10.0) {
        println("Invalid grade")
        break
      }
    }
}