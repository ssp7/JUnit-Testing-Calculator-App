|Sr. No.| Testing Requirements | Expected Output| 
| ------ | ------ | ---------|
|1| The program should ask for 2 integers as an input and give the command to perform a function as a type(+,-,/,*) | 1 1 + = ("2.0") | 
|2| If the input is just an integer then the program will convert the input as a textual representation and store in the stack with responding null | 1 ("null") |
|3|If the input integer exceeds 20 chars then the program will respond to token with "... too long" in the end |111111111111111111111 11 + = ("1111111111111111... too long") |
|4|If the input integer has 20 chars then the program will break with responding null | 1111111111111111111 11 + = ("null")|
|5|If the input of type is +,-,/,* then it will pop a value off the stack |eg, 1 1 + = ("2.0") |
|6|If the input of type is / but any of the integer is 0 then it will respond by printing "zero divisor popped" else it will function |eg  5 0 / = ("zero divisor popped") or 4 2 / = ("2.0")|
|7|If type is the character = then the function will return a tab character followed by a textual representation of the value at the top of the stack; the stack will remain unchanged.|1 1 + = 2.0 = 2.0
|8|If type is the character c then the function will pop the top value off the stack and return null. |eg  1 1 + = ("2.0"), = ("null")
|9|If type is any other value then the function will return “unknown command” followed by type (interpreted as a character).|eg 1 1 g = ("unkown command")
|10|If the functions tries to pop and empty stack then it will respond by printing "error: empty stack"|eg, + = ("error: empty stack")
|11|The stack is limited to 100 values. If the function attempts to push a value onto a full stack then the function will output “error: stack full” and return null.| entering 1 100 times and when you try to enter 1 one more time it will present the ("error: stack full")
|12|when the character type is q the whole porgram will terminate|1 1 + = ("2"), q (end)
|13|Runs the main function in calculator.java in order to run getToken()|9 3 - = ("6.0")
|14|Using setFakeIn with an arbitrarily large input should throw an error message | 1234567898765432345678765432 =q ("12345678987654323456... too long")
|15|Using setFakeIn with a decimal in order to reach the case where the input is not an integer | 2.9 3.0 + =q (5.9)
