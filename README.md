# Repita portal - Receptor

<div>
  <img width="200px" src="https://avatars0.githubusercontent.com/u/70340698?s=200&v=4" />
  <img width="200px" src="https://github.com/Hugo-Carvalho/repita-rpa-maker/blob/master/src/assets/title.PNG" />
</div>
<br />

### Receptor que ira vincular com o console de gerenciamento para a execução de aplicações JAVA

Desenvolvidor com <a href="https://spring.io" target="_blank">Spring</a>, responsavel por aguardar a chamada via HTTP do console para a execução de um cronograma, sendo assim, executando a aplicação JAVA vinculada e retornando o resultado.

O receptor depende do console (<a href="https://github.com/SikLabTech/repita-portal-console">click aqui para acessar o repositório</a>).

## Execução

Apenas o execute e garanta que a porta 38888 ou a que foi configurada no receptor estaja liberada para que o console possa conectar.

## Produção

Recomendamos executar o JAR com 1Gb de reserva de memoria da JVM, para isso execute o comando ``java -jar`` acrescido da opção ``-Xms1024M``.
