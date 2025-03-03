package intro

import chisel3._
import circt.stage.ChiselStage

class myXOR extends Module {
    val io = IO(new Bundle {
        val x   = Input(Bool())
        val y   = Input(Bool())
        val result   = Output(Bool())
    })
    io.result := io.x ^ io.y
    printf("      X  Y  Result\n")
    printf("dut: %d %d %d\n", io.x, io.y, io.result)
}

object Main extends App {
    println(getVerilogString(new myXOR()))
    println("Hardware generated")
    emitVerilog(new myXOR(), Array("--target-dir", "generated"))
}