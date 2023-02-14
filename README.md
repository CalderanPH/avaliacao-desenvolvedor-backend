# Avaliação Desenvolvedor Back-end Attornatus

O objetivo deste documento é identificar seus conhecimentos quanto às tecnologias utilizadas no cotidiano de desenvolvimento da equipe de Back-end na Attornatus Procuradoria Digital.

Esta análise propõe avaliar os seguintes temas: 
-	Qualidade de código
-	Java, Spring boot
-	API REST
-	Testes

A entrega deverá ser feita da seguinte forma:
-	O prazo para entrega da avaliação será de até 7 dias após envio da mesma
-	Encaminhar este documento com as perguntas respondidas e com o link do código público em sua conta do GitHub
-Opcionalmente, caso você consiga fazer o build da aplicação, poderá também informar o link de acesso


## **Qualidade de código**

#### 1.	Durante a implementação de uma nova funcionalidade de software solicitada, quais critérios você avalia e implementa para garantia de qualidade de software?
Requisitos: Verificar se os requisitos da funcionalidade estão claros e completos;
Testes: Criar casos de teste que cubram todos os cenários possíveis e garantam a funcionalidade correta do software;
Documentação: Documentar a funcionalidade e seu comportamento para futuros desenvolvedores e usuários;
Performance: Medir e otimizar o desempenho da funcionalidade para garantir que ela funcione de forma eficiente em diferentes cenários de uso;
Segurança: Verificar se a funcionalidade é segura e protegida contra possíveis vulnerabilidades;
Manutenibilidade: Garantir que a funcionalidade seja fácil de manter e corrigir no futuro, se necessário.

#### 2.	Em qual etapa da implementação você considera a qualidade de software?
A qualidade do software é considerado em todas as etapas da implementação, desde a criação, manutenção e suporte. Importante ter um bom Planejamento, garantindo que o software terá uma boa arquitetura, codificação, teste e implantação.


## Desafio Java

Usando Spring boot, crie uma API simples para gerenciar Pessoas. Esta API deve permitir:  
-	Criar uma pessoa
-	Editar uma pessoa
-	Consultar uma pessoa
-	Listar pessoas
-	Criar endereço para pessoa
-	Listar endereços da pessoa
-	Poder informar qual endereço é o principal da pessoa  

Uma Pessoa deve ter os seguintes campos:  
-	Nome
-	Data de nascimento
-	Endereço:
-	Logradouro
-	CEP
-	Número
-	Cidade

Requisitos  
-	Todas as respostas da API devem ser JSON  
- Banco de dados H2
