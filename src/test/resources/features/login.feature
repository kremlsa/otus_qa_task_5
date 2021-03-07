# language: ru

Функциональность: Проверка аутентификации пользователя
  В качестве аутентифицированного пользователя
  я хочу иметь возможность произвести вход на сайт
  чтобы получить доступ к разрешённым мне разделам сайта
  при этом, должны выполняться требования по безопасности

  @TestUI
  Сценарий: Аутентификация с корректными учётыми данными
    Дано  Страница входа
    Когда пользователь вводит корректные данные
    То пользователь осуществляет вход на сайт