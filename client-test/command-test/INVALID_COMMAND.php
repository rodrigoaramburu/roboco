<?php 

declare(strict_types=1);

include('../RobocoClientTest.php');

/**
 * Teste no Basico 1
 */

$roboco = new RobocoClientTest('rodrigo');

$response = $roboco->sendRawCommand(['command' => 'INVALID']);

print_r($response);