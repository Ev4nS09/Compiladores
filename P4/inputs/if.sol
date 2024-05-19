
void main()
begin
int _integer = 1;
real _double = 2.2;
string _string = "STRING";
bool _boolean = true;

if _integer == 1 then
    print "integer, true";
if _double == 2.2 then
    print "double, true";
if _string == "STRING" then
    print "string, true";
if _boolean == true then
    print "bool, true";

if _integer == 1 then
    if _double == 2.2 then
        if _string == "STRING" then
            if _boolean == true then
                print "nested ifs, true";
else
    print "O.o";

end


