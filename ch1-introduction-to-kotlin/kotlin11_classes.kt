open class Person constructor(var name: String, var isMale: Boolean, var age: Int = 0) {

    //Class Methods
    fun speak(sentence: String) {
        if (age < 3) {
            println("hello world")
        } else {
            println(sentence)
        }
    }

    open fun introduce() = println("Hi, my name is $name. I am $age years old and I am sex $gender.")
    
    // computed property
    val gender: String
        get() {
          if (isMale) {
            return "male"
          } else {
            return "female"
          }
        }

    //reserved words within the class that enable properties and methods that can be accessed directly.
    companion object {

        // Class property (static)
        var animalClass: String = "mammal"

        // Class Methods
        fun getInfo() : String {
        return "Person: ${Person.animalClass} who has name, gender and age"
        }
    }      
}

// Inheritance
class Student : Person {

    // Student class local property
    var rm = String()

    constructor (name: String, isMale: Boolean, age: Int = 0, rm: String) : super(name, isMale, age) {
    // Assigning the value to the Student class's local property
    this.rm = rm
    }

    override fun introduce(): Unit {
    super.introduce()
    // Accessing Student Class Local Property Information
    println("my RM at this school is $rm")
    }
}

fun main() {
    
    // Instantiating the Person class
    var pac = Person("Jonas Souza", true)

    // Printing values before changing age
    pac.introduce()

    // Changing a property of pac
    pac.age = 32

    // Printing values after changing the age
    pac.introduce()

    // Using the speak method
    pac.speak("Kotlin Training")
    
    println(pac.gender)

    println(Person.animalClass)

    println(Person.getInfo())

    var student = Student("Mary White", false, 10,"97663")
    student.introduce();    
  
}