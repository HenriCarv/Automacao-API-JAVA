  #language: pt
  #Author: Henrique de Carvalho
  #Data de Criacao: 11/11/2023
  #Version: 1.0
  #Encoding: UTF-8

  @mobile
  Funcionalidade: Abrir Conta

    @Teste02
    Cenario: Validar login invalido
      Dado que esteja na tela de login
      Quando eu preencher o numero da conta "00000000000"
      E clicar em CONTINUAR
