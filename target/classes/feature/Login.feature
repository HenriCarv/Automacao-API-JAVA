  #language: pt
  #Author: Nilton Moraes
  #Data de Criacao: 11/11/2022
  #Version: 1.0
  #Encoding: UTF-8

  @mobile
  Funcionalidade: Abrir Conta

    @Teste02
    Cenario: Validar login invalido
      Dado que esteja na tela de login
      Quando eu preencher o numero da conta "00310056080"
      E clicar em CONTINUAR
