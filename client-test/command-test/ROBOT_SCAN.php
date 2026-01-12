<?php 

declare(strict_types=1);

include('../RobocoClientTest.php');

/**
 * Teste no Nível Basico 1
 */

$roboco = new RobocoClientTest('rodrigo');

$response = $roboco->sendRawCommand(['command' => 'ROBOT_SCAN', 'value'=>'FRONT']);
echo "SCAN FRONT\n";
print_r($response);
echo "------------------------\n";

$response = $roboco->sendRawCommand(['command' => 'ROBOT_SCAN', 'value'=>'RIGHT']);
echo "SCAN RIGHT\n";
print_r($response);
echo "------------------------\n";

$response = $roboco->sendRawCommand(['command' => 'ROBOT_SCAN', 'value'=>'BACK']);
echo "SCAN BACK\n";
print_r($response);
echo "------------------------\n";

$response = $roboco->sendRawCommand(['command' => 'ROBOT_SCAN', 'value'=>'LEFT']);
echo "SCAN LEFT\n";
print_r($response);
echo "------------------------\n";


$response = $roboco->sendRawCommand(['command' => 'ROBOT_SCAN', 'value'=>'CENTER']);
echo "SCAN - invalid relative direction\n";
print_r($response);
echo "------------------------\n";