### **1. Почему на любом объекте можем вызвать метод `getClass()`?**

Метод `getClass()` определён в классе `java.lang.Object`, который является **суперклассом всех классов** в Java. Поскольку любой класс в Java неявно наследуется от `Object`, каждый объект автоматически получает метод `getClass()`.

- **Определение метода**:
  ```java
  public final native Class<?> getClass();
  ```
- Этот метод возвращает объект класса `Class`, который описывает тип данного объекта. Это позволяет получить полную информацию о классе объекта во время выполнения (например, его имя, методы, поля).

#### **Пример:**
```java
String str = "Hello";
System.out.println(str.getClass().getName());
```

---

### **2. Почему на любом классе можем вызвать `.class`?**

Операция `.class` — это **синтаксический элемент языка Java**, который позволяет получить объект типа `Class` для конкретного класса во время компиляции. Этот объект предоставляет метаинформацию о классе.

- **Пример**:
  ```java
  Class<String> stringClass = String.class;
  System.out.println(stringClass.getName());
  ```

- `.class` используется для работы с рефлексией и при создании динамических прокси.
- Таким образом, выражение `String.class` создаёт объект `Class`, представляющий класс `String`.

---

### **3. В чём отличие динамического прокси от статического?**

#### **Статический прокси**

Статический прокси — это прокси, который **создаётся вручную** (то есть его класс пишется программистом). Он реализует тот же интерфейс, что и реальный объект, и **делегирует вызовы методов реальному объекту** с возможностью добавить дополнительную логику (например, логирование, кеширование).



- **Преимущества статического прокси**:
  1. **Простота** реализации.
  2. Легко читать и отлаживать.

- **Недостатки статического прокси**:
  1. Требуется вручную писать класс прокси для каждого интерфейса.
  2. Если интерфейс изменится, придётся обновлять прокси-класс.


#### **Динамический прокси**

Динамический прокси создаётся **в рантайме с помощью стандартного API Java** (`java.lang.reflect.Proxy`). Он перехватывает вызовы методов интерфейса и позволяет добавлять к ним дополнительную логику без необходимости создания отдельных классов для прокси.



- **Преимущества динамического прокси**:
  1. **Не нужно создавать отдельные классы** для прокси — всё создаётся автоматически.
  2. Поддерживает **универсальность**: один и тот же прокси может использоваться для множества интерфейсов.
  3. Хорошо подходит для задач логирования, кеширования, транзакций и других аспектов.

- **Недостатки динамического прокси**:
  1. Работает **только с интерфейсами**. Если нужно проксировать классы, придётся использовать библиотеки типа **CGLIB**.
  2. **Сложнее отлаживать**, так как логика прокси скрыта в динамически сгенерированном коде.
  3. Может быть **медленнее**, чем статический прокси, из-за необходимости использовать рефлексию.