# language: ru

Функциональность: Проверка аутентификации пользователя
  В качестве аутентифицированного пользователя
  я хочу иметь возможность произвести вход на сайт,
  при этом в случае неуспшной аутентификации вход не будет выполнен
  и пользователь получит предупреждение.

  @TestUI
  Сценарий: Аутентификация с корректными учётыми данными
    Дано  Страница входа
    И Корректные учётные данные
    Когда пользователь вводит логин и пароль
    То пользователь осуществляет вход на сайт

  @TestUI
  Структура сценария: Аутентификация с некорректными учётыми данными
    Дано  Страница входа
    И логин '<login>'
    И пароль '<password>'
    Когда пользователь вводит логин и пароль
    То пользователь получает предупреждение
    Примеры:
      | login  | password |
      | admin  | admin    |
      | root   | root     |
      | test   | qwerty   |

