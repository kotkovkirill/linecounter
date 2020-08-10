This project is line counting utility for java code.
Internally it uses state machine implemented in java.io.StreamTokenizer with addition of ignoring empty lines.
If intention of this trial task was to check candidate's ability to work with state machines, I can replace StreamTokenizer with my custom implementation in no time.

Usage:

```
mvn package
java -classpath target/test-1.0-SNAPSHOT.jar com.codelinecounter.PathCounter src
```

This produces following output:


```
src : 153
    main : 85
        java : 85
            com : 85
                codelinecounter : 85
                    LineCounter.java : 31
                    PathCounter.java : 54
        resources : 0
    test : 68
        java : 60
            com : 60
                codelinecounter : 60
                    LineCounterTest.java : 35
                    PathCounterTest.java : 25
        resources : 8
            TestEmpty.java : 0
            TestGeneric1.java : 5
            TestGeneric2.java : 3
```
