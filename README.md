Para executar o projeto: 

1 - Instalar postgres
	https://www.postgresqltutorial.com/install-postgresql/

2 - Alterar o arquivo application.properties, linha 12, 13 e 14 com os dados de conexão com o banco. 
	O DER se encontra na raiz do projeto.

3 - Executar o script de criação das tabelas que se encontra na raiz do projeto.

4 - Executar o comando "gradlew bootRun" para iniciar a aplicação (é necessário ter o gradle instalado).

5 - No script, há um insert de um usuário para acessar o sistema:
	Username: admin
	Password: 1234
