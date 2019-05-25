package com.functional.training

import com.functional.training.Command.MoveForward
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test

class HandleCommandsTests  {
    @Test
    fun `direction nord and  move forward`() {

        val rover = handleCommand(MoveForward, Rover(1, 1, Direction.NORTH),Planet
        (Dimension(10,10)))

        Assert.assertThat(rover, CoreMatchers.`is`(Rover(1, 2, Direction.NORTH)))
    }

    @Test
    fun `direction nord and move backward`() {

        val rover = handleCommand(Command.MoveBackward, Rover(2, 2, Direction.NORTH),Planet
        (Dimension(10,10)))

        Assert.assertThat(rover, CoreMatchers.`is`(Rover(2, 1, Direction.NORTH)))
    }

    @Test
    fun `direction nord and turn right`() {

        val rover = handleCommand(Command.TurnRight, Rover(1, 1, Direction.NORTH),Planet( Dimension(10, 10)))

        Assert.assertThat(rover, CoreMatchers.`is`(Rover(1, 1, Direction.EAST)))
    }

    @Test
    fun `direction nord and turn left`() {

        val rover = handleCommand(Command.TurnLeft, Rover(1, 1, Direction.NORTH),Planet(Dimension(10, 10)))

        Assert.assertThat(rover, CoreMatchers.`is`(Rover(1, 1, Direction.WEST)))
    }


    @Test
    fun `wrapping from one edge of the grid to another moving north`() {

        val rover = handleCommand(
                command = MoveForward,
                rover   = Rover(1, 4, Direction.NORTH),
                planet  = Planet(Dimension(5, 5)))

        Assert.assertThat(rover, CoreMatchers.`is`(Rover(1, 1, Direction.NORTH)))
    }

    @Test
    fun `wrapping from one edge of the grid to another moving south`() {

        val rover = handleCommand(
                command = MoveForward,
                rover   = Rover(1, 1, Direction.SOUTH),
                planet  = Planet(Dimension(5, 5)))

        Assert.assertThat(rover, CoreMatchers.`is`(Rover(1, 5, Direction.SOUTH)))
    }

    @Test
    fun `wrapping from one edge of the grid to another moving east`() {

        val rover = handleCommand(
                command = MoveForward,
                rover   = Rover(4,1, Direction.EAST),
                planet  = Planet(Dimension(5, 5)))

        Assert.assertThat(rover, CoreMatchers.`is`(Rover(1, 1, Direction.EAST)))
    }

    @Test
    fun `wrapping from one edge of the grid to another moving west`() {

        val rover = handleCommand(
                command = MoveForward,
                rover   = Rover(1,1, Direction.WEST),
                planet  = Planet(Dimension(5, 5)))

        Assert.assertThat(rover, CoreMatchers.`is`(Rover(5, 1, Direction.WEST)))
    }





}
