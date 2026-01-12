<?php 

declare(strict_types=1);

include('../RobocoClientTest.php');

$roboco = new RobocoClientTest('rodrigo');

$finished = false;
while(!$finished){
    $roboco->sendRawCommand(['command' => 'ROBOT_MOVE']);
    $response = $roboco->sendRawCommand(['command' => 'SYSTEM_IS_FINISH']);
    if($response['code'] === 'LEVEL_FINISHED'){
        $finished = true;
    }
}
echo "Ganhei!!!\n";
$roboco->sendRawCommand(['command' => 'SYSTEM_DISCONNECT']);
