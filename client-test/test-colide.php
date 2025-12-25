<?php 

declare(strict_types=1);

include('ClientRoboco.php');

$roboco = new RobocoClient('rodrigo');


$roboco->roboMove();
$roboco->roboMove();
$roboco->roboMove();
$roboco->roboMove();

// Deve mover e bater na parede