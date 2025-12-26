<?php 

declare(strict_types=1);

include('../ClientRoboco.php');

$roboco = new RobocoClient('rodrigo');

while(!$roboco->isFinish()){
    $roboco->roboMove();
}
echo "Ganhei!!!\n";
$roboco->disconnect();
