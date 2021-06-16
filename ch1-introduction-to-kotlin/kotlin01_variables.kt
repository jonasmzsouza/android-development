fun main() {
    // One-line comments
    // Number of students per classroom

    var room1 = 13
    var room2 = 14
    var room3 = 15

    var total = room1 + room2 + room3
    println(total)

    /*
    * Block comments.
    * We can type multiple explanations 
    * in the same comment block
    */

    room1 = 16
    room2 = 17
    room3 = 18

    total = room1 + room2 + room3
    println(total)
    
    // Variables and Constants
    
    // Using lowerCamelCase. Each new word starts with a capital letter.
    var firstName = "Jonas"
    var lastName = "Souza"
    println (firstName)
    println (lastName)
    
    // Creating constants
    val pi = 3.141592
    val gravity = 9.81

    var value1 = 500       //Aqui, a inferência é para Int
    var value2: Int = 500  //Declaração explícita

    // Introducing value1
    println(value1)
    // Introducing value2
    println(value2)
}