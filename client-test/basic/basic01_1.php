<?php 

declare(strict_types=1);

include('../RobocoClientTest.php');

$roboco = new RobocoClientTest('rodrigo');

$finished = false;
while(!$finished){
    $roboco->sendRawCommand(['command' => 'ROBOT_MOVE']);
    print_r($response);
    $response = $roboco->sendRawCommand(['command' => 'SYSTEM_IS_FINISH']);
    print_r($response);
    if($response['code'] === 'LEVEL_FINISHED'){
        $finished = true;
    }
}
echo "Ganhei!!!\n";
$roboco->sendRawCommand(['command' => 'SYSTEM_DISCONNECT']);
