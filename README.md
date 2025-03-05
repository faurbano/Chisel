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


Para la comprender la instalación de Chisel, recomiendo visitar el sitio oficial: [Sitio Oficial de Chisel](https://www.chisel-lang.org)





![Compuerta XOR](https://github.com/faurbano/Chisel/blob/main/images/myXOR.png?raw=true)

