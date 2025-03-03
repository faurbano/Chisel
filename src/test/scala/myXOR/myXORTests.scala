package intro

import chisel3._
import chisel3.util._
import chiseltest._
import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec

class Simple extends Module{
    val io = IO(new Bundle {
        val a = Input (UInt(2.W))
        val b = Input (UInt(2.W))
        val out = Output (UInt(2.W))
        val equ = Output (Bool())
    })

    io.out := io.a & io.b
    io.equ := io.a === io.b
}

class myXORTest extends AnyFlatSpec with ChiselScalatestTester {
  "myXOR" should "pass" in {
    test(new myXOR) { dut =>
      dut.io.x.poke(0.B)
      dut.io.y.poke(0.B)
      dut.io.result.expect(0.B)  // 0 ^ 0
      println("Result is: " + dut.io.result.peekBoolean())
      dut.clock.step()

      dut.io.x.poke(0.B)
      dut.io.y.poke(1.B)
      dut.io.result.expect(1.B)  // 0 ^ 1
      println("Result is: " + dut.io.result.peekBoolean())
      dut.clock.step()

      dut.io.x.poke(1.B)
      dut.io.y.poke(0.B)
      dut.io.result.expect(1.B)  // 1 ^ 0
      println("Result is: " + dut.io.result.peekBoolean())
      dut.clock.step()

      dut.io.x.poke(1.B)
      dut.io.y.poke(1.B)
      dut.io.result.expect(0.B)  // 1 ^ 1
      println("Result is: " + dut.io.result.peekBoolean())
      println("Results OK\n")
      dut.clock.step()
    }
  }
}