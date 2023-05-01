1. Build artifacts and run tests

```
$ mvn package
```

2. Run application
```
$ java -jar target/hm03-0.0.1-SNAPSHOT.jar
```

3. Run tests
```
$ mvn test -Dtest=QuizDaoFromFileImplTest

$ mvn test -Dtest=IOServiceImplTest
```
