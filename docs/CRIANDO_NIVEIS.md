# Criando Níveis Personalizados

Para criar um novo nível para o RoboCo devemos estender a classe abstrata `br.dev.botecodigital.level.Level` implementado os métodos: `getFloor()`, `getObstacle()`, `getMarkers()`, `getName()`, `getDescription()`, `getRobotInitialPosition()`, `getRobotDirection()`, `boolean isFinished(Robot robot)`.

Para ter acesso a classe `Level` e outras necessárias para implementação do nível devemos adicionar o JAR do projeto do RoboCo disponível na pasta lib do pacote do projeto. No *maven* fazemos isso adicionando ele no diretório local através do comando:

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

Este método deve retornar uma matriz de objetos `Tile` de 10x10 onde o primeiro indice representa a linha no caso o eixo Y do mundo e o segundo indice representa a coluna no caso o eixo X do mundo.

A matriz "`floor`" será desenhada como o piso do nível e o robô será desenhado acima dele. É mais estético.

### `Tile[][] getObstacle()`

Este método deve retornar uma matriz de objetos `Tile` de 10x10 onde o primeiro indice representa a linha no caso o eixo Y do mundo e o segundo indice representa a coluna no caso o eixo X do mundo. 

A matriz "`getObstacle`" representa as paredes e obstáculos do mundo, se o robô tentar mover para uma posição que tenha um objeto Tile (um valor não nulo) ele irá colidir. **Valores nullos** representam posições sem nenhum obstaculo e estão livros para o robô passar por eles.

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

### `Map<Vector2, Tile> getMarkers()`

Este método deve retornar um Mapa, onde a chave deve ser um objeto `Vector2` que seus valores x,y representam a posição do marcador sendo o canto inferior esquerdo o ponto (0,0) e o ponto superior direito o (9,9). O valor `Tile` é a imagem do marcador que será desenhada. A posição é dada no eixo x e y. Os marcadores não são bloqueantes, o robô pode passar por cima deles e serão desenhados acima do Tiles retornados por `getFloor()`

### `MapDirection getRobotDirection()`

Deve retornar a direção inicial do robô no nível. O valores podem ser MapDirection.UP, MapDirection.RIGHT, MapDirection.DOWN, MapDirection.LEFT.

### `boolean isFinished(Robot robot)`

Deve retornar `true` se o nível foi finalizado e `false` caso contrário. Este método é chamado após cado comando executado pelo robô.

### `void dispose()`

Utilizado para liberar recurso ao finalizar o level, como chamar os métodos `dispose` dos objetos de `Texture` ou `Tile` instanciados no level.

## Tiles

Para facilitar a criação dos níveis é fornecido classes com diversas constantes de *Tile* prontos para utilizar:

- [FabricaTiles](fabrica-tiles/FABRICA_TILES.md): Conjunto de *Tiles* com a temática de fábrica.

### Tiles personalizados

Também podemos criar *tiles* personalizados instanciando um objeto da classe `br.dev.botecodigital.level.tiles.TileImage`, `br.dev.botecodigital.level.tiles.TileText` ou `br.dev.botecodigital.level.tiles.TileAnimate`.

#### TileImage

Um *tile* simples com uma imagem. Ele possui diversos construtores sobrecarregados. 

**Lembrando que a imagem de textura deve ter as dimensões 50px por 50px.**

**`TileImage(Texture texture)`**: recebe um objeto Texture do LibGdx
**`TileImage(TextureRegion textureRegion)`**: recebe um objeto TextureRegion do LibGdx.
**`TileImage(Texture texture, TileDiretion tileDiretion)`**: recebe um `Texture` e um valor do *enum*  `TileDirection` (UP, RIGHT, DOWN, LEFT, FLIPY, FLIPX), permitindo rotacionar a imagem de textura.
**`TileImage(ClassLoader classLoader, String assetFile)`** : Permite carregar uma imagem de textura(`assetFile`) do classpath do projeto, normalmente a pasta `resources`. Ex.: `new TileImage(getClass().getClassLoader(), "meus-tiles/tile01.png")`.

#### TileText

É um *tile* semelhante ao TileImage, mas permite a escrita de um pequeno texto sobre a imagem de textura. 

Possui os mesmo construtores com adição de um argumento String representando o texto a ser escrito sobre a imagem.

##### TileAnimate

Este é um tile que permite animação. 

**`TileAnimate(TextureRegion[] frames, float frameDuration)`**: Recebe um *array* de `TextureRegion` da LibGdx, sendo cada um  `TextureRegion` um dos *frames* da animação. O `frameDuration` é um tempo em segundos que cada *frame* da animação ficará na tela.

**`TileAnimate(ClassLoader classLoader, String assetFile, float frameDuration)`**: Semelhante ao construtor anterior, mas carrega os *frames* através de uma imagem de textura do classpath.

Ex.: `new TileAnimate(getClass().getClassLoader(), "assets/animado.png", 0.25f)`;

Cada área de 50px por 50px da imagem de textura será um *frame* da animação. Sendo uma imagem de textura de 150px por 150px, os frames ficará da sequinte maneira.

```
|1|2|3|
|4|5|6|
|7|8|9|
```

# Gerando o `jar`

Ao gerar o `.jar` do(s) nível(is) criados, necessitamos criar também a pasta `META-INF/services` e dentro dela criar um arquivo chamado `br.dev.botecodigital.level.Level` (que é o nome canonico da classe de Level). Dentro deste arquivo colocamos todas as classes de Level que implementamos com seu nome canonico.

Ex.:
```
my-level.jar
    - mylevel.levels
        - LevelA.class
        - LevelB.class
    - META-INF
        - services
            - br.dev.botecodigital.level.Level 
```

O arquivo `br.dev.botecodigital.level.Level` deverá ter o conteudo:

```
mylevel.levels.LevelA
mylevel.levels.LevelB
```

* OBS.: a criação da pasta META-INF é fundamental para o RoboCo carregar os níveis presentes no `jar`