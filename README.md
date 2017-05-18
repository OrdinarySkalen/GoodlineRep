
# Гаврилов Артем  [![Build Status](https://travis-ci.org/OrdinarySkalen/GoodlineRep.svg?branch=master)](https://travis-ci.org/OrdinarySkalen/GoodlineRep)

### Описание приложения
  Данное приложение осуществляет доступ к ресурсам авторизированному пользователю. Пример вызова приложения из командной строки:
   ```
   ./RUN.sh "-login XXX -pass XXX"
   ```
   
### Инструкция по сборке
Для сборки проекта воспользуйтесь коммандой - mvn package. Команда package — упаковывает скомпилированые классы в удобноперемещаемый формат (jar или war)

### Инструкция по запуску
Выполнить из командной строки файл RUN.sh с параметрами (или без, в данном случае будет показан help).

### Инструкция по тестированию
Для тестирования проекта воспользуйтесь коммандой - mvn test. Команда test используется для выполнения JUnit тестов проекта.

### Инструкция по отчетам: 
   * [Checkstyle](https://maven.apache.org/plugins/maven-checkstyle-plugin/usage.html).
   * [Findbugs](http://gleclaire.github.io/findbugs-maven-plugin/usage.html).
   * [Cobertura](http://www.mojohaus.org/cobertura-maven-plugin/usage.html). 
   * [PMD](https://maven.apache.org/plugins/maven-pmd-plugin/usage.html). 
   
 Для того чтобы сформировать отчеты воспользуйтесь коммандой - mvn site.
 
* Ссылка на [GitPages](https://ordinaryskalen.github.io/GoodlineRep/).
* [ROADMAP - план разработки приложения](./ROADMAP.md)
* [ROADMAP2 - план разработки документации](./ROADMAP2.md)
* [ROADMAP3 - план перевода приложения на работу с БД и добавления логирования](./ROADMAP3.md)
* [ROADMAP4 - план сборки приложения с использованием maven и применение JInit тестирования](./ROADMAP4.md)
