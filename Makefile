#Verilog code generation
doit:
		sbt run

#Running tests
test:
		sbt test

#Running XOR simulation and VCD file generation.
test_myXOR:
		sbt 'testOnly intro.myXORTest -- -DwriteVcd=1'

run_myXOR:
		sbt 'runMain intro.Main'

clean:
	        git clean -fd