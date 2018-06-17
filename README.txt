README

1 - INSTALAR MONGODB
!-- lembre-se de criar a pasta db na raiz da instalacao do mongoDB  --!
2 - Executar MAIN
3 - Abrir aplicacao pelo browser - url: localhost:8089

UTIL:
Parametros 
Arquivo: application.properties
#PORTA DA APLICACAO
server.port=8089

#NUMERO DE EXECUCOES DO CRAWLER
loopProp=2

#URL PARA BUSCA DE LINKS
linkRef=http://www.ibm.com.br

MAIN:
java -jar alexmello-url-crawler-2.war

DESCRITIVO:
SpringBootApplication com finalidade de procurar por links em uma webpage parametrizavel
Aplicacao teste para processo seletivo IBM.

Developed by: Alex C. Mello
