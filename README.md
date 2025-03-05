# Chisel

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

   Scala se ejecuta sobre la Máquina Virtual de Java (JVM) y Chisel requiere una versión de Java 8 o superior. Sin embargo, Scala CLI requiere Java 17 o superior. Si no se cumple con este requisito, Scala CLI descargará la    versión 17, de lo contrario, siga los pasos:

     ```
      sudo apt install -y wget gpg apt-transport-https
      sudo wget -qO - https://packages.adoptium.net/artifactory/api/gpg/key/public | gpg --dearmor | tee /etc/apt/trusted.gpg.d/adoptium.gpg > /dev/null
      sudo echo "deb https://packages.adoptium.net/artifactory/deb $(awk -F= '/^VERSION_CODENAME/{print$2}' /etc/os-release) main" | tee /etc/apt/sources.list.d/adoptium.list
      sudo apt update
      sudo apt install temurin-17-jdk
    ```


Para la comprender la instalación de Chisel, recomiendo visitar el sitio oficial: [Sitio Oficial de Chisel](https://www.chisel-lang.org)





![Compuerta XOR](https://github.com/faurbano/Chisel/blob/main/images/myXOR.png?raw=true)

