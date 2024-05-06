real inputNumber = 121, squareRoot = 121/2, t = squareRoot;
t ;
while ((t - squareRoot) != 0) do
begin
    t = squareRoot;
    squareRoot = (t + (inputNumber / t)) / 2;
end
print "The square root of " + inputNumber + " is :" + squareRoot;
