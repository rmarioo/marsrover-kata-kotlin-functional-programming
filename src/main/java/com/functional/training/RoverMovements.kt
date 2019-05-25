package com.functional.training


fun rotateLeft(rover: Rover) =
        rover.copy(direction = turnLeft(rover.direction))

fun rotateRight(rover: Rover) =
        rover.copy(direction = turnRight(rover.direction))

fun moveForward(rover: Rover, planet: Planet): Rover
{
    return when(rover.direction)
    {
        is Direction.NORTH -> moveNorth(rover,planet)
        is Direction.SOUTH -> moveSouth(rover,planet)
        is Direction.EAST -> moveEast(rover,planet)
        is Direction.WEST -> moveWest(rover,planet)
    }
}

fun moveBackward(rover: Rover,planet: Planet): Rover
{
    return when(rover.direction)
    {
        is Direction.NORTH -> moveSouth(rover,planet)
        is Direction.SOUTH -> moveNorth(rover, planet)
        is Direction.EAST -> moveWest(rover, planet)
        is Direction.WEST -> moveEast(rover, planet)
    }
}

private fun turnRight(direction: Direction): Direction =
        when(direction) {
            is Direction.NORTH -> Direction.EAST
            is Direction.SOUTH -> Direction.WEST
            is Direction.WEST -> Direction.NORTH
            is Direction.EAST -> Direction.SOUTH
        }

private fun turnLeft(direction: Direction): Direction =
        turnRight(turnRight(turnRight(direction)))

private fun moveEast(rover: Rover, planet: Planet): Rover =
        if (rover.x+1 < planet.dimension.x) shiftX(rover, +1)
        else rover.copy(x = 1)

private fun moveWest(rover: Rover, planet: Planet): Rover =
        if (rover.x - 1 >= 1) shiftX(rover, -1)
        else rover.copy(x = planet.dimension.x)


private fun moveSouth(rover: Rover, planet: Planet) =
        if (rover.y - 1 >= 1) shiftY(rover, -1)
        else rover.copy(y = planet.dimension.y)

private fun moveNorth(rover: Rover, planet: Planet) =
        if (rover.y+1 < planet.dimension.y) shiftY(rover, +1)
        else rover.copy(y = 1)


private fun shiftY(rover: Rover, shift: Int) =  rover.copy(y = rover.y + shift)

private fun shiftX(rover: Rover, shift: Int) = rover.copy(x = rover.x + shift)