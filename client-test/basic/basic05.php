<?php 

declare(strict_types=1);

include('../RobocoClientTest.php');

$roboco = new RobocoClientTest('rodrigo');

$finished = false;
while(!$finished){
    $response = $roboco->sendRawCommand(['command' => 'ROBOT_SCAN', 'value'=>'RIGHT']);

    if($response['message'] === 'EMPTY'){
        $roboco->sendRawCommand(['command' => 'ROBOT_TURN_RIGHT']);
        $roboco->sendRawCommand(['command' => 'ROBOT_MOVE']);
    }else{
        $roboco->sendRawCommand(['command' => 'ROBOT_MOVE']);
    }
    
    $finished = $response['code'] === 'LEVEL_FINISHED';
}

