# Projeto de Chat TCP 👩‍💻

### Vantagens: 
+ Orientado a conexão
+ Full-duplex
+ Garante a entrega

### ⌨️ Sobre a implementação: 
Para a realização do Chat, foi utilizado o protocolo TCP, por ser orientado a conexão ele provê segurança no processo de troca de dados entre os clientes, bem como garante a entrega e equilíbrio no envio de dados, pois divide as informações em pequenos pacotes e assim, evita a sobrecarga na comunicação. Foi implementado duas classes, sendo a do Servidor e a do Cliente.

### 🚨Falhas: 
O programa apresenta algumas falhas, que até o presente momento não foi conseguido tratar. A principal falha é no broadcast de mensagens, onde o cliente que envia a mensagem também a recebe, e é somente enviado a primeira mensagem, as demais não são enviadas para os outros clientes e nem para o servidor.
