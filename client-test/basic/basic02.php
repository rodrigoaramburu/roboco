<?php 

declare(strict_types=1);

include('../RobocoClientTest.php');

$roboco = new RobocoClientTest('rodrigo');

$roboco->sendRawCommand(['command' => 'ROBOT_MOVE']);
$roboco->sendRawCommand(['command' => 'ROBOT_MOVE']);
$roboco->sendRawCommand(['command' => 'ROBOT_MOVE']);
$roboco->sendRawCommand(['command' => 'ROBOT_TURN_LEFT']);
$roboco->sendRawCommand(['command' => 'ROBOT_MOVE']);
$roboco->sendRawCommand(['command' => 'ROBOT_MOVE']);

