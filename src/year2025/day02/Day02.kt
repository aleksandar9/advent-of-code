package year2025.day02

import println
import readInput

fun main() {

    fun isInvalidId(id: String): Boolean {
        return if (id.length % 2 != 0) {
            false
        } else {
            id.substring(0, id.length / 2) == id.substring(id.length / 2, id.length)
        }
    }

    fun part1(input: List<String>): Long {
        var sum = 0L

        input[0].split(',').forEach {
            val range = it.split('-')
            for (i in range[0].toLong()..range[1].toLong()) {
                if (isInvalidId(i.toString())) {
                    sum += i
                }
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(pkg = "year2025/day02", name = "Day02_test")
    check(part1(testInput) == 1227775554L)
    check(part2(testInput) == 0)

    val input = readInput(pkg = "year2025/day02", name = "Day02")
    part1(input).println()
    part2(input).println()
}
