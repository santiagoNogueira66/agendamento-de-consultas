# agendamentos de consultas

Este trabalho tem por objeto o desenvolvimento de um sistema de agendamento de consultas.

### Downloads necessários

- Eclipse: [Baixe o Eclipse aqui](https://www.eclipse.org/downloads/)

- Tomcat 9: [Baixe o Tomcat 9 aqui](https://tomcat.apache.org/download-90.cgi)
- Pg admin: [baix o pg admin aqui](https://www.pgadmin.org/download/)

### Importando o Projeto para o Eclipse

1. Abra o Eclipse.
2. Clique em "File" > "Import".
3. Selecione "Existing Projects into Workspace" e clique em "Next".
4. Escolha o diretório onde o projeto está localizado.
5. Selecione o projeto e clique em "Finish".

### Configuração do Tomcat no Eclipse

Assista a este [vídeo tutorial](https://www.youtube.com/watch?v=GCc4ZQqnmVY) para aprender a configurar o Tomcat no Eclipse.

### configurado o SGBD 

1. Abra o pgAdmin e conecte-se ao servidor PostgreSQL.
2. Crie uma nova base de dados chamada av2-pi4.
3. Certifique-se de que a senha do usuário PostgreSQL configurada no persistence.xml corresponde à senha que você usa no seu ambiente.

### intruções para rodar o projeto :

1. Clique com o botão direito em cima do projeto.
2. Selecione "Run As" > "Run on Server".
3. Clique em "Next".
4. Selecione o servidor Tomcat configurado e clique em "Finish".
