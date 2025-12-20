<?php 

declare(strict_types=1);

include('ClientRoboco.php');

$roboco = new RobocoClient('rodrigo');

$roboco->roboMove();
$roboco->roboMove();
$roboco->disconnect();

$roboco = new RobocoClient('rodeney');

$roboco->roboMove();
$roboco->roboMove();
$roboco->disconnect();

// Deve mover o robo 2 vezes para frente, disconnectar e reconectar e mover 2 vezes novamente