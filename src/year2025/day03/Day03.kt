package year2025.day03

import println
import readInput

fun main() {

    fun calculateJoltage(batteryBank: List<Int>, num: Int): MutableList<Int> {
        return if (num == 0) {
            mutableListOf(batteryBank.max())
        } else {
            val max = batteryBank.dropLast(num).max()
            val maxIndex = batteryBank.indexOf(max)
            calculateJoltage(batteryBank.drop(maxIndex + 1), num - 1).apply {
                add(max)
            }
        }
    }

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

    fun part2(input: List<String>): Long {
        var sum = 0L

        input.forEach {
            val battery = it.map { it.digitToInt() }
            val joltage = calculateJoltage(battery, 11)
            sum += joltage.reversed().joinToString("").toLong()
        }

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(pkg = "year2025/day03", name = "Day03_test")
    check(part1(testInput) == 357)
    check(part2(testInput) == 3121910778619L)

    val input = readInput(pkg = "year2025/day03", name = "Day03")
    part1(input).println()
    part2(input).println()
}
