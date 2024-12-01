1) Почему на любом объекте можем вызвать метод getClass()?

   Потому что все классы неявно наследуются от <code>Object</code>, который содержит метод <code>getClass()</code>

2) Почему на любом классе можем вызвать .class?

   Так как у каждого класса есть его объектная версия <code>java.lang.Class</code>
   
3) В чём отличие динамического прокси от статического? Приведите преимущества и недостатки.

   Статический прокси заранее определен в коде

       Преимущества:
       1) работает быстрее динамического
       
       Недостатки:
       1) при изменении методов интерфейса, придется переписывать все прокси
       2) если у нас куча классов, которые нужно проксировать, то и прокси классов будет не меньше

   Динамисеский прокси создается динамически в runime

       Преимущества:
       1) при изменении методов интерфейса, придется не придется ничего переписывать
       2) не нужно создвать свой отдельный прокси для каждого класса
       
       Недостатки:
       1) бьет по производительности

   https://www.piaproxy.com/blog/dynamic-residential-proxy/static-vs.-dynamic-proxies-what%E2%80%99s-the-difference-and-how-to-choose.html
   
4) Есть ли разница в инициализации класса через new и статический метод newInstance()?

   <code>new</code> позволяет инициализировать классы только по статическому имени (то есть имя класса должн обыть известно на этапе компиляции)
   
   <code>newInstance()</code> позволяет инициализировать классы, имена которых мы узнаем в runtime

   https://stackoverflow.com/questions/4612386/what-is-the-difference-between-the-new-operator-and-class-newinstance
   
5) Можно ли с помощью рефлексии изменить значения полей аннотации?

   Да, можно

   https://rationaleemotions.wordpress.com/2016/05/27/changing-annotation-values-at-runtime/
    