package year2025.day03

import println
import readInput

fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0

        input.forEach {
            val battery = it.map { it.digitToInt() }
            val firstMax = battery.dropLast(1).max()
            val maxIndex = battery.indexOf(firstMax)
            val secondMax = battery.drop(maxIndex + 1).max()
            sum += firstMax * 10 + secondMax
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(pkg = "year2025/day03", name = "Day03_test")
    check(part1(testInput) == 357)
    check(part2(testInput) == 0)

    val input = readInput(pkg = "year2025/day03", name = "Day03")
    part1(input).println()
    part2(input).println()
}
