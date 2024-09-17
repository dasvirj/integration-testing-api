Feature: Listar um fato

  Scenario: Existe um fato para ser exibido
    Given um acesso a API
    When uma request GET é feita ao endpoint
    Then um fato é exibido