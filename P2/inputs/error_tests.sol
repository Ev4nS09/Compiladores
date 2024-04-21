/*
    Unary operator errors
*/
print not "not";
print not 1;
print not 1.0;
print - true;
print -"minus";

/*
    Incomparable types errors
*/
print 1 == true;
print 1 == "true";
print true == 1;
print "true" == 1;
print true == "true";
print 1 != true;
print 1 != "true";
print true != 1;
print "true" != 1;
print true != "true";

/*
    Binary operator errors
*/

//Sum
print 1 + true;
print 1.0 + true;
print true + true;

//Subtraction
print 1 - true;
print 1.0 - true;
print true - true;
print 1 - "true";
print 1.0 - "true";
print true - "true";
print "true" - "true";

//Multiplication
print 1 * true;
print 1.0 * true;
print true * true;
print 1 * "true";
print 1.0 * "true";
print true * "true";
print "true" * "true";

//Division
print 1 / true;
print 1.0 / true;
print true / true;
print 1 / "true";
print 1.0 / "true";
print true / "true";
print "true" / "true";

//Mod
print 1 % 1.0;
print 1 % true;
print 1.0 % true;
print true % true;
print 1 % "true";
print 1.0 % "true";
print true % "true";
print "true" % "true";

//Relational '>'
print 1 > true;
print 1.0 > true;
print true > true;
print 1 > "true";
print 1.0 > "true";
print true > "true";
print "true" > "true";

//Relational '>='
print 1 >= true;
print 1.0 >= true;
print true >= true;
print 1 >= "true";
print 1.0 >= "true";
print true >= "true";
print "true" >= "true";

//Relational '<'
print 1 < true;
print 1.0 < true;
print true < true;
print 1 < "true";
print 1.0 < "true";
print true < "true";
print "true" < "true";

//Relational '<='
print 1 <= true;
print 1.0 <= true;
print true <= true;
print 1 <= "true";
print 1.0 <= "true";
print true <= "true";
print "true" <= "true";

//and
print 1 and 1;
print 1 and 1.0;
print 1 and true;
print 1.0 and true;
print 1 and "true";
print 1.0 and "true";
print true and "true";
print "true" and "true";

//or
print 1 or 1;
print 1 or 1.0;
print 1 or true;
print 1.0 or true;
print 1 or "true";
print 1.0 or "true";
print true or "true";
print "true" or "true";

/*
    Multiple errors in a single line
*/
print ((true == 1) or (2 / "1")) + (675675 % 1231.345);
print true >= 23 + "true" - false * "ola";
print (not ((123 * 23) + "12")) - 12;




