int x = 0;
int i;
while x < 10 do
    begin
        if x == 5 then
            break;
        for i = 0 to 10 do
            begin
                if i % 2 == 0 then
                    print i + " is even";
                else
                    print i + " is odd";
                if i * 5 == 25 then
                    break;
            end
        print "for is over";
        if x == 3 then
            break;
    x = x + 1;
    end
print "program is over";