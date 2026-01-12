<?php 

declare(strict_types=1);

/**
 * Teste no Basico 1
 */

$socket = socket_create(AF_INET, SOCK_STREAM, 0);
socket_connect($socket, 'localhost', 9999);

$command = ['command'=>'ROBOT_MOVE'];

socket_write($socket, json_encode($command) . "\n");

$responseText = socket_read($socket, 1024);

$response = json_decode($responseText, true);

print_r($response);