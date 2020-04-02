# DojoPuzzles ATM Machine

This is my solution for the DojoPuzzles' ATM Machine puzzle (http://dojopuzzles.com/problemas/exibe/caixa-eletronico/)  
My first step to solving this puzzle was to make sure the main logic was done correctly, that is, the machine will always
return the least amount of notes in a withdrawal. When i structured the project my main concern was to make sure it is 
modular to some extent, that way there's room to future improvements with minimal changes to the project structure such as: New notes values(Like 5 and 2 Real notes). That's why i used Enums, and methods as generic as possible, the Cash machine class does not need to know which notes are supported, and that can be changed with minimal modifications. 
I've choose to have a finite number of notes in the machine.   

Remember! The ammount of notes is finite, but the machine will always try to give the smallest amount possible given the notes it haves.

Future improvements:
- A better way to track the number of stored bills, while maintaining the project simplicity
- Maybe making the algorithm more generic, so it can be reused in other similar applications, such as a vending machine (That implicates the support for coins, which does not make a lot of sense in a ATM machine, so it would need refactoring )

### Running:
- With Gradle: 
    1) first test the project! "gradle test" or "./gradlew test"
    2) Run the command "gradle run" or "./gradlew run"
- Building the .Jar:
    1) first test the project! "gradle test" or "./gradlew test"
    2) run "gradlew build" or "./gradlew build"
    3) run "java -jar build/libs/CashMachine-1.0-SNAPSHOT.jar" 
