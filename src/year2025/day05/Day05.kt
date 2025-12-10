package year2025.day05

import println
import readInput

fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0
        var ingredients = false
        val ranges = mutableListOf<LongRange>()

        input.forEach {
            if (it.isEmpty()) {
                ingredients = true
            } else if (ingredients) {
                val ingredient = it.toLong()
                if (ranges.any { range -> ingredient in range }) {
                    sum++
                }
            } else {
                val parts = it.split("-")
                ranges.add(parts[0].toLong()..parts[1].toLong())
            }
        }

        return sum
    }

    fun part2(input: List<String>): Long {
        val ranges = mutableListOf<Pair<Long, Long>>()

        for (string in input) {
            if (string.isEmpty()) {
                break
            }
            val parts = string.split("-")
            ranges.add(parts[0].toLong() to parts[1].toLong())
        }

        ranges.sortBy { it.first }

        val mergedRanges = mutableListOf<Pair<Long, Long>>()
        mergedRanges.add(ranges[0])

        for (i in 1..ranges.count() - 1) {
            val lastRange = mergedRanges.last()
            if (mergedRanges.last().second >= ranges[i].first - 1) {
                mergedRanges.removeLast()
                mergedRanges.add(
                    minOf(lastRange.first, ranges[i].first) to maxOf(
                        lastRange.second,
                        ranges[i].second
                    )
                )
            } else {
                mergedRanges.add(ranges[i])
            }
        }

        return mergedRanges.sumOf { it.second - it.first + 1 }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(pkg = "year2025/day05", name = "Day05_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 14L)

    val input = readInput(pkg = "year2025/day05", name = "Day05")
    part1(input).println()
    part2(input).println()
}
