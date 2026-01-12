<?php 

declare(strict_types=1);

include('../RobocoClientTest.php');

/**
 * Teste no Basico 1
 */

$roboco = new RobocoClientTest('rodrigo');

$response = $roboco->sendRawCommand(['command' => 'ROBOT_MOVE']);

$response = $roboco->sendRawCommand(['command' => 'SYSTEM_DISCONNECT']);

print_r($response);