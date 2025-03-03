module myXOR(
  input   clock,
  input   reset,
  input   io_a, // @[src/main/scala/myXOR/myXOR.scala 7:16]
  input   io_b, // @[src/main/scala/myXOR/myXOR.scala 7:16]
  output  io_c // @[src/main/scala/myXOR/myXOR.scala 7:16]
);
  assign io_c = io_a ^ io_b; // @[src/main/scala/myXOR/myXOR.scala 12:18]
endmodule
