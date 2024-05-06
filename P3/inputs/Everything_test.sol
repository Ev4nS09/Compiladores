int a = 1, b, c;

while a < 20 do
begin
    print "Doing b FOR: " ;
    for b=a to 10 do
    begin

        if b % 7 == 0 then
            break;

        print "Doing c FOR: " ;
        for c=b to 5 do
        begin
            if c % 15 == 0 then
                break;
            print "c: " + c;
        end
        print "b: " + b;
    end
    print "a: " + a;

    a = a + 1;
end