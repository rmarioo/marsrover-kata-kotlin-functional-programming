package com.functional.training

import com.functional.training.Direction.NORTH


data class Rover(val x: Int ,val y: Int, val direction: Direction = NORTH)

data class Planet(val dimension: Dimension)

data class Dimension(val x:Int,val y:Int)

data class CommandsResult(val rover: Rover)

sealed class Command
{
    object MoveForward: Command()
    object MoveBackward: Command()
    object TurnLeft: Command()
    object TurnRight: Command()
    object Exit: Command()
}

sealed class Direction {
    object NORTH : Direction()
    object EAST  : Direction()
    object SOUTH : Direction()
    object WEST : Direction()

    override fun toString(): String =
         when(this) {
            is NORTH  -> "N"
            is SOUTH -> "S"
            is EAST   -> "E"
            is WEST  -> "W"
        }

}
