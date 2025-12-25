# RoboCo

# TODO 0.1.0

[x] Criar classe esboço Robo com movimentação
[x] Criar mecanismo para receber comandos via socket
[x] Criar classe de cenário
[x] Tratar colisão do robo com objetos do cenário
[ ] Criar critério de finalização
[ ] Criar tela de abertura

## Socket API

Para mover o Robo primeiro conecata-se via Socket na porta 9999, e então são passados comando no formato JSON finalizados com \n.

O primeito comando a ser passado é:

{ target: "SYSTEM", command: "SETUSERNAME", value="<usuario>"}

### Comandos para o robo

{ target: "ROBO", command: "MOVE"}

{ target: "ROBO", command: "TURN_LEFT"}

{ target: "ROBO", command: "TURN_RIGHT"}

### Comando para o sistema

{ target: "SYSTEM", command: "DISCONNECT"}


### Respostas dos comandos

Todo comando gerará um resposta.

{ status: "<SUCCESS|ERROR>", codigo: "<codigo>", messge: "<messge>"}