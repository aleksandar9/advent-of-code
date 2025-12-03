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

    fun isInvalidId2(id: String, count: Int): Boolean {
        if (count > id.length / 2) return false
        if (id.length % count != 0) return isInvalidId2(id, count + 1)
        if (id.chunked(count).distinct().size == 1) {
            return true
        } else {
            return isInvalidId2(id, count + 1)
        }
    }

    fun part2(input: List<String>): Long {
        var sum = 0L

        input[0].split(',').forEach {
            val range = it.split('-')
            for (i in range[0].toLong()..range[1].toLong()) {
                if (isInvalidId2(i.toString(), 1)) {
                    sum += i
                }
            }
        }

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(pkg = "year2025/day02", name = "Day02_test")
    check(part1(testInput) == 1227775554L)
    check(part2(testInput) == 4174379265L)

    val input = readInput(pkg = "year2025/day02", name = "Day02")
    part1(input).println()
    part2(input).println()
}
