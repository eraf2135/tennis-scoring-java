## Overview
Allows you to score one standard tiebreak set playing deuce games.
i.e. tiebreak at 6 games all.

### Interface
```

  Match match = new Match("player 1", "player 2");
  match.pointWonBy("player 1");
  match.pointWonBy("player 2");
  // this will return "0-0, 15-15"
  match.score();

  match.pointWonBy("player 1");
  match.pointWonBy("player 1");
  // this will return "0-0, 40-15"
  match.score();
  
  match.pointWonBy("player 2");
  match.pointWonBy("player 2");
  // this will return "0-0, Deuce"
  match.score();
  
  match.pointWonBy("player 1");
  // this will return "0-0, Advantage player 1"
  match.score();
  
  match.pointWonBy("player 1");
  // this will return "1-0"
  match.score();
 
```
To test

    mvn test

To build jar and install

    mvn install
    
To exec the above sample, printing to the console:

    mvn exec:java -Dexec.mainClass="tennis.Match"

or 

    java -jar ./target/tennis-scoring-java-1.0-SNAPSHOT.jar
