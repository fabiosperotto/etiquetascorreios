# Gerador de Etiquetas para Correios

![Screenshot da aplicação que mostram as telas internas do sistema](./image.png 'Screenshot da aplicação')

Este é um projeto de aprendizado sobre programação desktop com Java, interfaces gráficas em Java Swing, ersistência de dados com Hibernate, Internacionalização e Exportação de dados para PDF. O sistema não deve ser utilizado para fins comerciais ou em produção, servindo apenas como referência instrucional em disciplinas de cursos da área.

## Instalação

Realize o download da última versão disponível. É necessário o JDK 18+ para rodar a aplicação. O diretório de execução do projeto precisa ter o executável .jar e o diretório de arquivos de idiomas.

## Funcionalidades

- Cadastro de pessoas (atualmente somente armazena o nome);
- Cadastro de endereços, com logradouro e número. Cada pessoa pode ter múltiplos endereços.
- Gerador de etiquetas: para cada endereço é possível gerar etiqueta de destinatário, abre o PDF automaticamente após cada solicitação de geração do arquivo. Existe espaço de melhoria aqui pois a etiqueta consta parcialmente pronta.

## Para Desenvolvedores

O projeto utiliza como gerenciador de dependência o Maven, sendo que a estrutura de diretórios consta como:

- DAO: classes que operam aspectos de banco de dados com as entidades;
- entities: classes entidades do sistema (Model do MVC);
- gui: classes de interfaces gráficas do sistema (Controller/View do MVC);
- services: classe de serviços de terceiros do sistema, aqui reside atualmente a classe que gera o PDF;
- utilities: classes utilitárias para todos os demais componentes do sistema, pode conter gerador de dados de teste, configurações de banco de dados, etc.

Após realizar o download para desenvolvimento, na raíz do projeto, renomeie o arquivo hibernate-exemplo.cfg.xml para hibernate.cfg.xml e informe as credenciais do seu banco de dados. A configuração atual irá criar o banco baseado nas classes entidades.

## Tecnologias e Conceitos Aplicados

- [Hibernate](https://hibernate.org/orm/) 6.6;
- MySQL 8.0;
- JDK 18+;
- [iText PDF](https://itextpdf.com/) 8.0.3;

### Banco de Dados

O presente projeto utiliza como SGBD o MySQL e por meio do Hibernate realiza as persistência dos objetos e o gerenciamento dos dados.

### Javadoc

Utiliza o plugin maven-javadoc-plugin para gerar o javadoc para fins de empacotamento no executável .jar (gera também o site com o HTML de documentação de toda a API do código).

### Geração de Executável

O projeto usa o plugin maven-assembly-plugin que gera executável com todo o empacotamente das dependências e javadoc no mesmo .jar.

### Geração de Etiquetas em PDF

Por meio do iText pe fornecido um exemplo de geração de etiquetas simples de envio de correspondência via correios.

### Internacionalização

O sistema atualmente consta em português e inglês e se apresenta automaticamente no idioma do usuário de acordo com o idioma do sistema operacional.

## Contribuindo

Pull requests são bem vindos. Para mudanças grandes abra uma issue primeiro para discutir as mudanças desejadas. Para outras colaborações, informe no seu pull request os detalhes e motivações na contribuição.

## Licença

[GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/)
