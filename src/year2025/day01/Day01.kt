package year2025.day01

import println
import readInput
import kotlin.math.abs

fun main() {

    fun calculateNewPosition(currentPosition: Int, input: String): Int {
        val direction = input[0]
        val distance = input.substring(1).toInt()
        val newPosition = if (direction == 'R') {
            (currentPosition + distance) % 100
        } else {
            (currentPosition - distance) % 100
        }
        return if (newPosition < 0) newPosition + 100 else newPosition
    }

    fun part1(input: List<String>): Int {
        var sum = 0

        var currentPosition = 50
        input.forEach {
            currentPosition = calculateNewPosition(currentPosition, it)
            if (currentPosition == 0) sum++
        }

        return sum
    }

    fun Int.calculate(newPosition: Int): Int {
        return if (newPosition >= 100) {
            (newPosition / 100)
        } else if (newPosition < 0) {
            val calc = abs(newPosition / 100) + 1
            if (this == 0) calc - 1 else calc
        } else if (newPosition == 0) {
            1
        } else {
            0
        }
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        var currentPosition = 50
        input.forEach {
            val direction = it[0]
            val distance = it.substring(1).toInt()

            val newPosition = if (direction == 'R') {
                currentPosition + distance
            } else {
                currentPosition - distance
            }

            sum += currentPosition.calculate(newPosition)

            val normalizedPosition = newPosition % 100

            currentPosition = if (normalizedPosition < 0) {
                normalizedPosition + 100
            } else {
                normalizedPosition
            }
        }

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(pkg = "year2025/day01", name = "Day01_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 6)

    val input = readInput(pkg = "year2025/day01", name = "Day01")
    part1(input).println()
    part2(input).println()
}
