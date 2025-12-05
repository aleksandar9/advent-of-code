package year2025.day04

import println
import readInput

fun main() {

    data class Node(
        val x: Int,
        val y: Int,
        var value: Char,
    )

    fun List<Node>.findAdjacentRolls(from: Node): List<Node> {
        val right = this.find { it.x == from.x + 1 && it.y == from.y && it.value == '@' }
        val left = this.find { it.x == from.x - 1 && it.y == from.y && it.value == '@' }
        val up = this.find { it.x == from.x && it.y == from.y - 1 && it.value == '@' }
        val down = this.find { it.x == from.x && it.y == from.y + 1 && it.value == '@' }
        val rightUp = this.find { it.x == from.x + 1 && it.y == from.y - 1 && it.value == '@' }
        val leftDown = this.find { it.x == from.x - 1 && it.y == from.y + 1 && it.value == '@' }
        val leftUp = this.find { it.x == from.x - 1 && it.y == from.y - 1 && it.value == '@' }
        val rightDown = this.find { it.x == from.x + 1 && it.y == from.y + 1 && it.value == '@' }

        return listOfNotNull(right, left, up, down, rightUp, leftDown, leftUp, rightDown)
    }

    fun List<Node>.printGrid() {
        val maxX = this.maxOf { it.x }
        val maxY = this.maxOf { it.y }
        for (y in 0..maxY) {
            for (x in 0..maxX) {
                print(this.find { it.x == x && it.y == y }?.value)
            }
            kotlin.io.println()
        }
    }

    fun part1(input: List<String>): Int {
        var sum = 0

        val grid = mutableListOf<Node>()
        input.forEachIndexed { y, string ->
            string.forEachIndexed { x, char ->
                grid.add(Node(x, y, char))
            }
        }

        sum = grid.count { it.value == '@' && grid.findAdjacentRolls(it).count() < 4 }

        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        val grid = mutableListOf<Node>()
        input.forEachIndexed { y, string ->
            string.forEachIndexed { x, char ->
                grid.add(Node(x, y, char))
            }
        }

        val freeRolls = mutableListOf<Node>()

        do {
            freeRolls.clear()

            grid.forEach {
                if (it.value == '@' && grid.findAdjacentRolls(it).count() < 4) {
                    freeRolls.add(it)
                }
            }

            grid.replaceAll { if (it in freeRolls) it.copy(value = 'x') else it }

            sum += freeRolls.size
        } while (freeRolls.isNotEmpty())

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(pkg = "year2025/day04", name = "Day04_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 43)

    val input = readInput(pkg = "year2025/day04", name = "Day04")
    part1(input).println()
    part2(input).println()
}
