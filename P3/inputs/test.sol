int x = 1;
int i = 2;

while x < 20 do
begin
    if x >= 100000 then
        break;
    for i = 0 to x do
        print x;
    if i == 19 then
        break;
    if x != 0 then
        if x % 2 == 0 then
            if (x / 2) % 2 == 0 then
                x = x * 2;
            else
                x = x + 1;
        else
            x = x + 1;
    else
        x = x + 1;
end