<?php 

declare(strict_types=1);

include('ClientRoboco.php');

$roboco = new RobocoClient('rodrigo');

try{
    $roboco->sendRawCommand(['alvo' => 'ROBO', 'command' => 'DAR_RE']);
    echo "Nenhuma mensagem de erro recebida";
}catch(\Exception $e){
    echo "Recebido erro com a mensagem: {$e->getMessage()}\n";
}
