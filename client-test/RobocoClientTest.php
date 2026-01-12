<?php 

declare(strict_types=1);

class RobocoClientTest
{

	private string $host;
	private int $port;
	private $socket = null;

	public function __construct(string $username, string $host = 'localhost', int $port = 9999){
		$this->host = $host;
		$this->port = $port;
		
		$this->socket = socket_create(AF_INET, SOCK_STREAM, 0);
		socket_connect($this->socket, $this->host, $this->port);
		
		$this->sendRawCommand(['command'=>'SYSTEM_SETUSERNAME','value'=> $username]);

	}
	
	public function sendRawCommand(array $command): array 
	{
		socket_write($this->socket, json_encode($command) . "\n");
		$responseText = socket_read($this->socket, 1024);
		$response = json_decode($responseText, true);
		
		return $response;
	}

}