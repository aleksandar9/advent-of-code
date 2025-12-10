package year2025.day06

import println
import readInput

fun main() {

    fun part1(input: List<String>): Long {
        val operators = input.last().trim().split("\\s+".toRegex())
        val results = operators.map { if (it == "+") 0L else 1L }.toMutableList()

        input.dropLast(1).forEach {
            val currentInputs = it.trim().split("\\s+".toRegex())
            operators.forEachIndexed { index, operator ->
                if (operator == "+") {
                    results[index] += currentInputs[index].toLong()
                } else {
                    results[index] *= currentInputs[index].toLong()
                }
            }
        }

        return results.sum()
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(pkg = "year2025/day06", name = "Day06_test")
    check(part1(testInput) == 4277556L)
    check(part2(testInput) == 0)

    val input = readInput(pkg = "year2025/day06", name = "Day06")
    part1(input).println()
    part2(input).println()
}
