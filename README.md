###How to run:
1. `mvn clean package`
2. `java -jar target/storware-task-1.0-SNAPSHOT.jar "FILE_PATH"`  
replace FILE_PATH with an absolute path to file with math expressions.  
example: java -jar target/storware-task-1.0-SNAPSHOT.jar "C:/testfile"

###Builtin testfile
There is example file used for tests - you can use it for demonstration with command:  
java -jar target/storware-task-1.0-SNAPSHOT.jar "src/test/resources/testfile"  

content:  
&nbsp;&nbsp;add 2  
&nbsp;&nbsp;subtract 5  
&nbsp;&nbsp;multiply 3  
&nbsp;&nbsp;divide 7  
&nbsp;&nbsp;apply 10  
expected output:  
&nbsp;&nbsp;3