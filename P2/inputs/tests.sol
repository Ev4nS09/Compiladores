/*
    Arithmetic operations
*/

print 5 + 3 * 2;                            //Resultado esperado: 11
print (5 + 3) * 2;                          //Resultado esperado: 16
print 10 / (2 + 3);                         //Resultado esperado: 2.0
print 7 % (1 + 4);                          //Resultado esperado: 2
print 5 + 3.5 * 2.2;                        // Resultado esperado: 12.7
print (5 + 3.5) * 2.2;                      // Resultado esperado: 19.8
print 10 / (2.5 + 3.7);                     // Resultado esperado: 1.25
print 7.3 * 3.2 * (1.8 + 4.2);              // Resultado esperado: 56.94
print 10 * (2 + 3) - 7 / 2;                 // Resultado esperado: 46.5
print - (5 + 3 * 2) + (4 * 2 - 1);          // Resultado esperado: -4
print - ((10 + 5) / (7 - 3));               // Resultado esperado: -3.75

/*
    String operations
*/

print "Hello, " + "world!";                 // Resultado esperado: "Hello, world!"
print "apple" == "apple";                   // Resultado esperado: true
print "apple" == "banana";                  // Resultado esperado: false
print "banana" != "orange";                 // Resultado esperado: true
print "orange" != "orange";                 // Resultado esperado: false
print 25 + " anos";                         // Resultado esperado: "25 anos"
print "pi = " + 3.1416 + " is" + true;      // Resultado esperado: "pi = 3.1416 istrue"

/*
    Logical operations
*/

//Compare operator 'and'
print true and false;                       //Resultado esperado: false
print true and true;                        //Resultado esperado: true
print false and false;                      //Resultado esperado: false

//Compare operator 'or'
print true or false;                        //Resultado esperado: true
print true or true;                         //Resultado esperado: true
print false and false;                      //Resultado esperado: false


/*
    Compare operations
*/

//Compare operator '=='
print 1 == 1;                               //Expected result: true
print 1 == 2;                               //Expected result: false
print 1 == 1.0;                             //Expected result: true
print 1 == 2.0;                             //Expected result: false
print "Maria" == "Maria";                   //Expected result: true
print "Maria" == "Jessica";                 //Expected result: false
print true == true;                         //Expected result: true
print true == false;                        //Expected result: false

//Compare operator '!='
print 1 != 1;                               //Expected result: false
print 1 != 2;                               //Expected result: true
print 1 != 1.0;                             //Expected result: false
print 1 != 2.0;                             //Expected result: true
print "Maria" != "Maria";                   //Expected result: false
print "Maria" != "Jessica";                 //Expected result: true
print true != true;                         //Expected result: false
print true != false;                        //Expected result: true

/*
    Unary operator
*/

//Unary operator "-"
print -1;                                   //Expected result: -1
print --1;                                  //Expected result: 1
print -1.0;                                 //Expected result: -1.0

//Unary operator "not"
print not true;                             //Expected result: false
print not false;                            //Expected result: true

/*
    Compare operators with extras
*/

print not ((true and false) or (10 < 5)) and (7 >= 7);                                  //Resultado esperado: true
print (10 + 5) * 2 - 4 == 18;                                                           //Resultado esperado: false
print ("abc" == "abc") or (3 < 2);                                                      //Resultado esperado: true
print "Win" + "dows" != "Windows" and ((10 / 2) == 5);                                  //Resultado esperado: false
print (3 * 2) % 5 == 1 and "Hello" == "Hello";                                          //Resultado esperado: true
print ((10 + 5) * 2 - 4 == 18) or (3 * 2 > 10);                                         //Resultado esperado: false
print ((10 >= 5) and (3 < 8)) or (true and not false);                                  //Resultado esperado: true
print (10 <= 5) or (3 > 8);                                                             //Resultado esperado: false
print "Win" + "Dows" != "Windows" or (10.2 - 3 == 1);                                   //Resultado esperado: true
print "\"Win\" + \"dows\" é igual a Windows? " + "Win" + "dows" == "Windows";           //Resultado esperado: false
print ("Win" != "win");                                                                 //Resultado esperado: true
print "Engenharia " + (true or false);                                                  //Resultado esperado: Engenharia true


