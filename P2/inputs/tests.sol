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


