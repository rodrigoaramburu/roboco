<?php 

declare(strict_types=1);

include('../ClientRoboco.php');

$roboco = new RobocoClient('rodrigo');

$roboco->roboTurnLeft();
$roboco->roboMove();
$roboco->roboTurnRight();
$roboco->roboMove();
$roboco->roboMove();
$roboco->roboMove();
$roboco->roboMove();
$roboco->roboTurnRight();
$roboco->roboMove();

if($roboco->finish()){
    echo "Ganhei!!!\n";
}else{
    echo "Perdi!!!\n";
}
