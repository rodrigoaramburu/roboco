# Criando Níveis Personalizados

Para criar um novo nível para o RoboCo devemos estender a classe abstrata `br.dev.botecodigital.level.Level` implementado os métodos: `getFloor()`, `getObstacle()`, `getName()`, `getDescription()`, `getRobotInitialPosition()`, `getRobotDirection()`, `boolean isFinished(Robot robot)`.

Para ter acesso a classe `Level` e outras necessárias para implementação do nível devemos adicionar o JAR projeto do RoboCo disponível na pasta lib do pacote do projeto. No *maven* fazemos isso adicionando ele no diretório local através do comando:

```bash
mvn install:install-file -Dfile=RoboCo-0.3.0.jar -DgroupId=roboco -DartifactId=RoboCo -Dversion=0.3.0 -Dpackaging=jar
```

E após adicionando a dependência no `pom.xml`.

```xml
<dependencies>
    <dependency>
        <groupId>roboco</groupId>
        <artifactId>RoboCo</artifactId>
        <version>0.3.0</version>
    </dependency>
</dependencies>

```


## Segue a descrição dos métodos a serem implementados

### `Tile[][] getFloor()` 

Este método deve retornar uma matriz de objetos Tile de 10x10 onde o primeiro indice representa a linha no caso o eixo Y do mundo e o segundo indice representa a coluna no caso o eixo X do mundo.

A matriz "floor" será desenhada como o piso do nível e o robô será desenhado acima dele. É mais estético.

### `Tile[][] getObstacle()`

Este método deve retornar uma matriz de objetos Tile de 10x10 onde o primeiro indice representa a linha no caso o eixo Y do mundo e o segundo indice representa a coluna no caso o eixo X do mundo. 

A matriz "getObstacle" representa as paredes e obstáculos do mundo, se o robô tentar mover para uma posição que tenha um objeto Tile (um valor não nulo) ele irá colidir. **Valores nullos** representam posições sem nenhum obstaculo e estão livros para o robô passar por eles.

### `String getName()`

Deve retornar o nome do nível. Este valor será exibido na tela inicial de escolha de nível.

### `String getDescription()`

Deve retornar uma descrição curta do nível. Este valor será exibido na tela inicial de escolha de nível.

### `Vector2 getRobotInitialPosition()`

Deve retornar um objeto `Vector2` a posição inicial do robô no nível. A posição é dada no eixo x e y, sendo o canto inferior esquerdo o ponto (0,0) e o ponto superior direito o (9,9)

```
y
   _ _ _ _ _ _ _ _ _ _ (9,9)
9 |_|_|_|_|_|_|_|_|_|_|
8 |_|_|_|_|_|_|_|_|_|_|
7 |_|_|_|_|_|_|_|_|_|_|
6 |_|_|_|_|_|_|_|_|_|_|
5 |_|_|_|_|_|_|_|_|_|_|
4 |_|_|_|_|_|_|_|_|_|_|
3 |_|_|_|_|_|_|_|_|_|_|
2 |_|_|_|_|_|_|_|_|_|_|
1 |_|_|_|_|_|_|_|_|_|_|
0 |_|_|_|_|_|_|_|_|_|_|
   0 1 2 3 4 5 6 7 8 9
            x
```

### `MapDirection getRobotDirection()`

Deve retornar a direção inicial do robô no nível. O valores podem ser MapDirection.UP, MapDirection.RIGHT, MapDirection.DOWN, MapDirection.LEFT.

### `boolean isFinished(Robot robot)`

Deve retornar `true` se o nível foi finalizado e `false` caso contrário. Este método é chamado após cado comando executado pelo robô.


## Tiles

Para facilitar a criação dos níveis é fornecido uma classe com diversas constantes de Tile prontos `TileManager`

# Gerando o `jar`

Ao gerar o `.jar` do(s) nível(is) criados, necessitamos criar também a pasta `META-INF/services` e dentro dela criar um arquivo chamado `br.dev.botecodigital.level.Level` (que é o nome canonico da classe de Level). Dentro deste arquivo colocamos todas as classes de Level que implementamos com seu nome canonico.

Ex.:

my-level.jar
    - mylevel.levels
        - LevelA.class
        - LevelB.class
    - META-INF
        - services
            - br.dev.botecodigital.level.Level 

O arquivo br.dev.botecodigital.level.Level deverá ter o conteudo

```
mylevel.levels.LevelA
mylevel.levels.LevelB
```

* OBS.: a criação da pasta META-INF é fundamental para o RoboCo carregar os níveis presentes no `jar`