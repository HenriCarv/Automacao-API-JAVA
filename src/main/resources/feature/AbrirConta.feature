  #language: pt
  #Author: Henrique de Carvalho
  #Data de Criacao: 11/11/2023
  #Version: 1.0
  #Encoding: UTF-8

  @mobile
    Funcionalidade: Abrir Conta

      @Teste01
      Cenario: Cenario: Validar abertura de conta
        Dado que esteja na tela de login
        Quando eu clicar em Abrir conta
        E clicar em ABRIR CONTA
        E preencher "Matheus Silva", "81334400000", "14/08/1980", "testeMobile@inm.com.br" e "11999995555"
        E clicar em Avancar



