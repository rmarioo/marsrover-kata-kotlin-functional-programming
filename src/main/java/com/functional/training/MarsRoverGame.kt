package com.functional.training

import arrow.effects.IO
import arrow.effects.extensions.io.fx.fx
import arrow.effects.extensions.io.monad.binding
import arrow.syntax.collections.tail
import com.functional.training.Command.*

fun run(): IO<Unit> {

    return fx {
                           !welcome()
        val planet       = !readPlanet()
        val rover        = !readRover()
        val commands     = !readCommands()
        val result       = handleCommands(commands,rover,planet)
        val display      = !display(result)
        display
    }
}

fun welcome(): IO<Unit>
  = puts("welcome to mars rover kata")


fun readPlanet(): IO<Planet> {
    return binding {
        val (x) = askInt("planet width ?")
        val (y) = askInt("planet height ?")
        Planet(Dimension(x,y))
    }
}


fun readRover(): IO<Rover> {
    return binding {
        val (x) = askInt("rover position x ?")
        val (y) = askInt("rover position y ?")
        Rover(x,y)
    }
}

fun readCommands(): IO<List<Command>> =
        readCommands(listOf())


fun readCommands(commands: List<Command>): IO<List<Command>> {
    return binding {
        val (command) = readCommand()
        val (result) =
                when (command) {
                    is Exit -> IO { commands }
                    else -> readCommands(commands.plus(command))
                }
        result
    }
}

fun readCommand(): IO<Command> =
    ask("command ?").map { parseCommand(it.get(0)) }


fun handleCommands(commands: List<Command>, rover: Rover, planet: Planet):CommandsResult {

    return if (commands.isEmpty())
        CommandsResult(rover)
    else {
        val nextRover: Rover = handleCommand(commands.get(0), rover, planet)
        handleCommands(commands.tail(),nextRover,planet)
    }
}

fun handleCommand(command: Command, rover: Rover, planet: Planet): Rover {
    return when(command) {
            is MoveForward  -> moveForward(rover,planet)
            is MoveBackward -> moveBackward(rover,planet)
            is TurnRight    -> rotateRight(rover)
            is TurnLeft     -> rotateLeft(rover)
            is Exit         -> rover.copy()
        }
}


fun display(commandsResult: CommandsResult): IO<Unit> {
    return puts("${commandsResult.rover.direction}:${commandsResult.rover.x},${commandsResult
            .rover.y}")
}

fun parseCommand(c: Char): Command =
    when (c) {
        'l' -> TurnLeft
        'r' -> TurnRight
        'f' -> MoveForward
        'b' -> MoveBackward
        else -> Exit
    }

