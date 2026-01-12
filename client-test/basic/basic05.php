<?php 

declare(strict_types=1);

include('../ClientRoboco.php');

$roboco = new RobocoClient('rodrigo');

while(!$roboco->isFinish()){
    if($roboco->roboScan('RIGHT') === 'EMPTY'){
        $roboco->roboTurnRight();
        $roboco->roboMove();
    }else{
        $roboco->roboMove();
    }
}

