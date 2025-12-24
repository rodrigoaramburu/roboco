<?php 

declare(strict_types=1);

include('../ClientRoboco.php');

$roboco = new RobocoClient('rodrigo');

$roboco->roboMove();
$roboco->roboMove();
$roboco->roboMove();
$roboco->roboTurnLeft();
$roboco->roboMove();
$roboco->roboMove();
$roboco->disconnect();
