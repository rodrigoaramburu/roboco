<?php

declare(strict_types=1);

$socket = socket_create(AF_INET, SOCK_STREAM, 0);
socket_connect($socket, 'localhost', 9999);

$command = ['target'=>'ROBO','command'=>'MOVE'];

socket_write($socket, json_encode($command) . "\n");

$responseText = socket_read($socket, 1024);

$response = json_decode($responseText, true);

if($response['status'] === 'ERROR'){
    echo "Retornado erro com a mensagem: {$response['message']}\n";
}else{
    echo "Não foi retornado um erro\n";
}

// Deve retornar o erro "Retornado erro com a mensagem: Username not set"