#Verilog code generation
doit:
		sbt run

#Running tests
test:
		sbt test

test_myXOR:
		sbt 'testOnly intro.myXORTest -- -Dwrite Vcd=1'

run_myXOR:
		sbt 'runMain intro.Main'