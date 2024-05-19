
void main()
begin

//Variable test

print "solUtils.Variable test";
int x = 0, c;
real b;
b = 11;
print "x =" + x;
print "b =" + b;
print "x - b = " + x - b;

// if test

int x = 10;
if (x > 5) then
    print "x é maior que 5";
else
    print "x é menor ou igual a 5";


// While loop withouth break

print "While loop withouth break";
int x = 0;
while (x < 5) do
begin
    print x;
    x = x + 1;
end

// While loop withouth break

print "While loop with break";
int x = 0;
while (x < 20) do
begin
    print x;
    x = x + 1;
    if x == 10 then
      break;
end

//For loop

int i, j;
for i = 0 to 2 do
begin
    for j = 0 to 2 do
    begin
        print "i * j =" + i * j;
    end
end

//For loop with break

int i, j;
for i = 0 to 5 do
begin
print "i = " + i
    for j = 0 to 4 do
    begin
        print "j = " + j;
        if (j > 2) then
        begin
          print "j passou dos 2";
          break;
        end
        print "i * j =" + i * j;
        
    end
end

end
