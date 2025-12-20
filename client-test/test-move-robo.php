<?php 

declare(strict_types=1);

include('ClientRoboco.php');

$roboco = new RobocoClient('rodrigo');

$roboco->roboMove();
$roboco->roboMove();
$roboco->roboMove();
$roboco->roboMove();
$roboco->roboTurnRight();
$roboco->roboMove();
$roboco->roboMove();
$roboco->roboTurnLeft();
$roboco->roboMove();
$roboco->disconnect();

// Deve mover o robo 4 vezes para frente, virar a direita, mover 2 vezes para frente, viara a esquerda e mover uma vez e 
// disconectar;