<?php 

declare(strict_types=1);

class RobocoClient{

	private string $host;
	private int $port;
	private $socket = null;

	public function __construct(string $username, string $host = 'localhost', int $port = 9999){
		$this->host = $host;
		$this->port = $port;
		
		$this->socket = socket_create(AF_INET, SOCK_STREAM, 0);
		socket_connect($this->socket, $this->host, $this->port);
		
		$this->sendRawCommand(['target'=>'SYSTEM', 'command'=>'SETUSERNAME','value'=> $username]);

	}
	
	public function sendRawCommand(array $command): array 
	{
		socket_write($this->socket, json_encode($command) . "\n");
		$responseText = socket_read($this->socket, 1024);
		$response = json_decode($responseText, true);
		if($response['status'] !== 'SUCCESS'){
			throw new Exception($response['message']);
		}
		return $response;
	}

    public function roboMove(): array
    {
       return $this->sendRawCommand(['target'=>'ROBO','command'=>'MOVE']);
    }

    public function roboTurnLeft(): array
    {
       return $this->sendRawCommand(['target'=>'ROBO','command'=>'TURN_LEFT']);
    }

    public function roboTurnRight(): array
    {
       return $this->sendRawCommand(['target'=>'ROBO','command'=>'TURN_RIGHT']);
    }
		
	public function disconnect(): void
	{
		$response = $this->sendRawCommand(['target'=>'SYSTEM', 'command'=>'DISCONNECT']);
	}

	public function isFinish(): bool
	{
		$response = $this->sendRawCommand(['target'=>'SYSTEM', 'command'=>'IS_FINISH']);
		return $response['status'] === 'SUCCESS' && $response['code'] === 'LEVEL.FINISHED';
	}
		
}