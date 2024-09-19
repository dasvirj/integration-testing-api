Feature: Teste de integração com cucumber e rest assured

  Scenario: Traduzir para Valiriano
    Given uma frase
    When envio a frase para a API de traducao Valyriana
    Then o status code da API deve retornar 200
    And a frase deve ser retornada traduzida para Valiryan