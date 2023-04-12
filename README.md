# POC-Kafka com Resource, Consumer e Producer
Este repositório contém uma prova de conceito (POC) de como usar o Apache Kafka para implementar uma arquitetura de mensageria para troca de informações entre diferentes componentes de um sistema distribuído.

A POC é composta por três módulos: um recurso (Resource), um consumidor (Consumer) e um produtor (Producer). O objetivo do recurso é simular um sistema que gera informações que precisam ser compartilhadas com outros componentes. O produtor é responsável por publicar as informações geradas pelo recurso no Kafka. O consumidor, por sua vez, é responsável por consumir as informações publicadas pelo produtor e processá-las de acordo com as regras de negócio do sistema.

Pré-requisitos
Java 11 ou superior
Apache Kafka 2.5 ou superior
Gradle 6 ou superior
Como executar a POC
Antes de executar a POC, é preciso iniciar o servidor do Kafka. Para isso, basta executar o seguinte comando no terminal:

``` bash
bin/kafka-server-start.sh config/server.properties
```

Para executar a POC, siga os seguintes passos:

Clone este repositório em sua máquina local:

``` bash
git clone https://github.com/seu-usuario/poc-kafka.git
```

Entre na pasta do projeto:

bash

``` bash
cd poc-kafka
```

Compile o projeto com o seguinte comando:

``` bash
gradle clean build
```
Inicie o recurso, que irá gerar informações a serem compartilhadas com outros componentes:

``` bash
java -jar resource/build/libs/resource-1.0.0.jar
```
Inicie o produtor, que irá publicar as informações geradas pelo recurso no Kafka:

Pronto! Agora você tem um sistema distribuído que usa o Kafka como plataforma de mensageria para troca de informações entre seus componentes.
