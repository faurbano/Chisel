# Chisel HDL

## Chisel (Constructing Hardware in a Scala Embedded Language)

<p align='justify'>Es un lenguaje de descripción hardware de código abierto creado por la Universidad de Berkeley en el 2012. Chisel está basado en Scala y es una biblioteca de definiciones de clases especiales y objetos predefinidos que hereda los aspectos de programación funcional y orientada a objetos de Scala para la descripción de hardware.</p>

<p align='justify'>Los circuitos digitales descritos en Chisel, son convertidos a Verilog para síntesis y simulación.</p>

## Instalación

Para la instalación, se presentan los pasos en entorno Linux (También se puede instalar en MacOS y Windows)

1. Instalar Scala CLI

   ```curl -sS "https://virtuslab.github.io/scala-cli-packages/KEY.gpg" | sudo gpg --dearmor  -o /etc/apt/trusted.gpg.d/scala-cli.gpg 2>/dev/null
      sudo curl -s --compressed -o /etc/apt/sources.list.d/scala_cli_packages.list "https://virtuslab.github.io/scala-cli-packages/debian/scala_cli_packages.list"
      sudo apt update
      sudo apt install scala-cli
   ``` 
2. Descargar el ejemplo de Chisel en Scala CLI:
   
   `curl -O -L https://github.com/chipsalliance/chisel/releases/latest/download/chisel-example.scala`

3. Usar Scala CLI para compilar y ejecutar el ejemplo:

   `scala-cli chisel-example.scala`

4. Verificar la versión de *Java Development Kit* (JDK)

     `java -version`

   Scala se ejecuta sobre la Máquina Virtual de Java (JVM) y Chisel requiere una versión de Java 8 o superior. Sin embargo, Scala CLI requiere Java 17 o superior. Si no se cumple con este requisito, Scala CLI descargará la    versión 17, de lo contrario, seguir siguientes los pasos:

     ```
      sudo apt install -y wget gpg apt-transport-https
      sudo wget -qO - https://packages.adoptium.net/artifactory/api/gpg/key/public | gpg --dearmor | tee /etc/apt/trusted.gpg.d/adoptium.gpg > /dev/null
      sudo echo "deb https://packages.adoptium.net/artifactory/deb $(awk -F= '/^VERSION_CODENAME/{print$2}' /etc/os-release) main" | tee /etc/apt/sources.list.d/adoptium.list
      sudo apt update
      sudo apt install temurin-17-jdk
    ```

5. Se requiere de una herramienta de compilación, personalmente uso SBT (Scala Build Tool). Revisar la versión y cambiar si es necesario:

   ```
      curl -s -L https://github.com/sbt/sbt/releases/download/v1.9.7/sbt-1.9.7.tgz | tar xvz
      sudo mv sbt/bin/sbt /usr/local/bin/
   ```

6. Para simulación se requiere Verilator. Usualmente las distribuciones de Linux lo traen incluido, si no es el caso, se debe instalar:

   `sudo apt install -y verilator`

7. Otra herramienta importante es GTKWave, que permite la visualización de formas de onda para comprender mejor las simulaciones. Para instalar, se deben realizar los siguientes pasos: 


Para más información sobre Chisel en [Sitio Oficial][1].

<!--- <p><a href="https://www.chisel-lang.org/"; target="_blank"> Descargar Chisel </a></p> -->

## Compuerta Lógica XOR

<p align='justify'> La compuerta XOR, también conocida como OR Exclusiva, se representa como en la Figura 1. Las entradas <code>X</code> y <code>Y</code>, y la salida <code>result</code>, son de un bit.<!p> 

![Compuerta XOR](https://github.com/faurbano/Chisel/blob/main/images/myXOR.png)

Figura 1. Compuerta XOR.

En la Tabla 1, se puede observar el comportamiento de la compuerta XOR.

| X | Y | result |
|---|---|:------:|
| 0 | 0 |   0    |
| 0 | 1 |   1    |
| 1 | 0 |   1    |
| 1 | 1 |   0    |

Tabla 1. Comportamiento de la XOR.

## Estructura de Chisel

Vamos a revisar la estructura básica del lenguaje mediante el ejemplo de la compuerta XOR:

```Scala
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
}

```

Recordemos que un `package`, para agrupar clases relacionadas. La librería `chisel3._`, se encarga de importar todas las instrucciones disponibles para la elaboración de los circuitos digitales.

Como toda clase, `class myXOR` define las características y comportamiento de la entidad. La variable io (inmutable), se define como la entidad, donde se describen las entradas tipo Bool, ya que solo tienen dos posibles valores. En este caso `X` y `Y`; y la salida `result`. Posteriormente se usa el operador `^`, que es el que realiza la operación lógica XOR.

La librería `circt.stage.ChiselStage` es la encargada de las instrucciones para generar el hardware (Verilog).

```Scala
   object Main extends App {
   println(getVerilogString(new myXOR()))
   println("Hardware generated")
   emitVerilog(new myXOR(), Array("--target-dir", "generated"))
   }
```

La sintaxis `getVerilogString`, permite imprimir en pantalla, el código Verilog generado; y `emitVerilog(new myXOR(), Array("--target-dir", "generated"))`, sintetiza el archivo y con `Array`, permite configurar en que directorio se almacenará el archivo generado.

Para generar el hardware, se puede hacer mediante la instrucción `sbt run`, que ejecutará todos los proyectos disponibles. Si se desea, solo ejecutar el proyecto actual, o sea myXOR, se puede hacer mediante `sbt 'runMain intro.Main'`, o ejecutar el Makefile que he diseñado, `make run_myXOR`.

Para depuración y pruebas (simulación), Chisel tiene el paquete `chiseltest` que es una extensión de ScalaTest. En `src/test/scala/myXOR
/myXORTests.scala`, está el Script que realiza la simulación. El método `test()` usa el circuito, llamado DUT ("Device Under Test"), como parámetro y el código de pruebas que le inyectará las señales a la entidad (DUT).

```Scala
   dut.io.x.poke(0.B)
   dut.io.y.poke(0.B)
   dut.io.result.expect(0.B)  // 0 ^ 0
   println("Result is: " + dut.io.result.peekBoolean())
   dut.clock.step()
```

Se pueden establecer valor en los puertos de entrada (`X` y `Y`), a través de `poke` que lo toma como tipo Chisel. En este caso, se usan valores tipo Booleanos (B), toma a 0 como `False`; y a 1 como `True`. Los datos de los puertos de salida se pueden leer usando el método `peekBoolean`, o `peekInt`. Como el circuito es combinacional, requiere en la verificación, indicar intervalos de tiempo para ir enviando las señales a las entradas y obtener la respuesta, para ello se usa `clock.step`.

Para ejecutar la simulación se puede hacer mediante, uno de los siguientes métodos:

1. `sbt test`. Ejecuta todas las simulaciones disponibles.
2. `sbt "testOnly intro.myXORTest"`. Ejecuta únicamente la clase myXORTest.


## Simulación

Para visualizar las simulaciones de los circuitos generados, se puede usar GTKWave. Si se desea instalar en Linux, se debe proceder de la siguiente manera:

```Linux
   sudo apt-get update
   sudo apt-get -y install gtkwave
```

La simulación de Chisel puede exportarse en un archivo en formato VCD (IEEE 1364-1995), que contiene toda la información de la simulación en formas de onda (Waveform), ideal para hacer depuración. Para generar el archivo, se puede adicionar al comando de simulación, `sbt 'testOnly intro.myXORTest -- -DwriteVcd=1`; y el archivo se exporta al directorio `test_run_dir/myXOR_should_pass/`. Una vez ya se tiene listo el archivo, se procede a abrirlo con GTKWave ejecutando el comando, `gtkwave test_run_dir/myXOR_should_pass/myXOR.vcd`.

![Simulación de myXOR en GTKWave](https://github.com/faurbano/Chisel/blob/main/images/myXOR_gtkwave.png)

Figura 2. Simulación de myXOR en GTKWave. 


## Flujo de Trabajo de Chisel

![Flujo de Trabajo de Chisel](https://github.com/faurbano/Chisel/blob/main/images/chisel_flow.png)

Figura 2. Flujo de Trabajo de Chisel.


Referencias

[1]: https://www.chisel-lang.org "Sitio Oficial"

