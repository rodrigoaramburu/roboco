<?php 

declare(strict_types=1);

include('ClientRoboco.php');

$roboco = new RobocoClient('rodrigo');

$roboco->roboScan("FRONT");

$roboco->roboScan("LEFT");

$roboco->roboScan("RIGHT");

$roboco->roboScan("BACK");
