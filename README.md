# RoboCo (alpha)

O **RoboCo** é uma ferramenta para auxiliar o ensino de programação, inspirada nas atividades do **code.org**, onde um robozinho é movido pela tela percorrendo labirintos.

Os comandos enviados ao robô são realizados por meio de uma **conexão via socket**, possibilitando a criação de APIs clientes em diversas linguagens de programação.

> ⚠️ **Status:** Projeto em fase *alpha*. A API e os comandos podem sofrer alterações.

---

## Socket API

Para controlar o RoboCo, o cliente deve se conectar via **Socket** na porta **9999**.

Após a conexão, os comandos são enviados como mensagens de texto no formato **JSON**, sempre finalizadas com o delimitador de quebra de linha (`\n`).

### Estrutura do comando

Cada comando enviado possui os seguintes campos:

- **command**: comando a ser executado;
- **value** *(opcional)*: valor associado ao comando.

Exemplo genérico:

```json
{ "command": "<COMMAND>", "value": "<VALUE>" }
```

## Resposta da API

Após o envio e a execução de um comando, o servidor retorna uma mensagem de resposta por meio da conexão *socket*.

A resposta contém os seguintes campos:

- **status**: indica se o comando foi processado com sucesso (`SUCCESS`) ou se ocorreu algum erro (`ERROR`);
- **code**: código interno que representa o resultado do comando;
- **message**: mensagem descritiva sobre o resultado da operação.

Formato da resposta:

```json
{ "status": "<SUCCESS|ERROR>", "code": "<code>","message": "<message>" }
```

## Configuração inicial

O **primeiro comando obrigatório** após a conexão com o servidor é o `SYSTEM_SETUSERNAME`, utilizado para identificar o usuário que está realizando a atividade.

```json
{ "command": "SYSTEM_SETUSERNAME", "value": "<usuario>"}
```

### Comandos para o robô 

Os comandos abaixo permitem controlar robô:

#### ROBOT_MOVE

Move o robô uma "casa" para frente, na direção a atual do robô.

```json
{ "command": "ROBOT_MOVE" }
```

Como resposta de sucesso é retornado:

```json
{ "status": "SUCCESS", "code": "ROBOT_ACTION_OK","message": "Robo moveu para frente." }
```

Ou caso o robo colida:

```json
{ "status": "ERROR", "code": "ROBOT_COLLISION","message": "O robo colidiu!" }
```

#### ROBOT_TURN_LEFT

Gira o robô 90 graus para a esquerda em relação à direção atual.

```json
{ "command": "ROBOT_TURN_LEFT"}
```

Como resposta de sucesso é retornado:

```json
{ "status": "SUCCESS", "code": "ROBOT_ACTION_OK","message": "Robo virou a esquerda." }
```

#### ROBOT_TURN_RIGHT

Gira o robô 90 graus para a direita em relação à direção atual.

```json
{ "command": "ROBOT_TURN_RIGHT"}
```

Como resposta de sucesso é retornado:

```json
{ "status": "SUCCESS", "code": "ROBOT_ACTION_OK","message": "Robo virou a direita." }
```

### ROBOT_SCAN 

Verifica o ambiente em uma dada direção para identificar obstáculos. Direções disponíveis FRONT, LEFT, RIGHT

```json
{ "command": "ROBOT_SCAN", "value": "<direção>"}
```

Retorna 
```json
{ "status": "SUCCESS", "code": "SCAN_SUCCESS","message": "<EMPTY|WALL>" }
```

### Comandos para o sistema

Além dos comandos para interagir com o robô, o cliente pode enviar comandos para o sistema.

#### SYSTEM_IS_FINISH

Verifica se o nível foi concluído.

Os possíveis códigos retornados na resposta são:

* LEVEL_FINISHED
* LEVEL_NOT_FINISHED

```json
{ "command": "SYSTEM_IS_FINISH"}
```

#### DISCONNECT

Realiza a desconeção do cliente *socket*

```json
{ "command": "SYSTEM_DISCONNECT"}
```


