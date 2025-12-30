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

- **target**: define o alvo do comando (`ROBO` ou `SYSTEM`);
- **command**: comando a ser executado;
- **value** *(opcional)*: valor associado ao comando.

Exemplo genérico:

```json
{ "target": "<ROBO|SYSTEM>", "command": "<COMMAND>", "value": "<VALUE>" }
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

O **primeiro comando obrigatório** após a conexão com o servidor é o `SETUSERNAME`, utilizado para identificar o usuário que está realizando a atividade.

```json
{ "target": "SYSTEM", "command": "SETUSERNAME", "value": "<usuario>"}
```

### Comandos para o robô (`target: ROBO`)

Os comandos abaixo permitem controlar robô:

#### MOVE

Move o robô uma "casa" para frente, na direção a atual do robô.

```json
{ "target": "ROBO", "command": "MOVE"}
```

#### TURN_LEFT

Gira o robô 90 graus para a esquerda em relação à direção atual.

```json
{ "target": "ROBO", "command": "TURN_LEFT"}
```

#### TURN_LEFT

Gira o robô 90 graus para a direita em relação à direção atual.

```json
{ "target": "ROBO", "command": "TURN_RIGHT"}
```

### Comandos para o sistema (`target: SYSTEM`)

Além dos comandos para interagir com o robô, o cliente pode enviar comandos para o sistema.

#### IS_FINISH

Verifica se o nível foi concluído.

Os possíveis códigos retornados na resposta são:

* FINISHED
* NOT_FINISHED

```json
{ "target": "SYSTEM", "command": "IS_FINISH"}
```

#### DISCONNECT

Realiza a desconeção do cliente *socket*

```json
{ "target": "SYSTEM", "command": "DISCONNECT"}
```


