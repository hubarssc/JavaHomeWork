package homework12

fun main() {

    val reversedString = "hello".reverse()
    println(reversedString) // Выведет: "olleh"

    val number = 10
    val isNumberEven = number.isEven()
    println(isNumberEven) // Выведет: true

    val doubleNumber = 5.0
    val squaredNumber = doubleNumber.square()
    println(squaredNumber) // Выведет: 25.0
}

private fun String.reverse(): String {
    return this.reversed()
}

private fun Int.isEven(): Boolean {
    return this % 2 == 0
}

private fun Double.square(): Double {
    return this * this
}


