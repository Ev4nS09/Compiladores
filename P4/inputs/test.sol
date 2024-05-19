void main()
begin

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
                    begin
                        print i + "x" + i + " is 25";
                        break;
                    end
            end
        print "for is over";
    x = x + 1;
    if x == 3 then
        break;
    end
print "program is over";

end