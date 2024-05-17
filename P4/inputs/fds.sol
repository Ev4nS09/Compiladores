int recursive(int x)
begin
    if (x < 1000000000) then
        return recursive(x + 1);
    else
        return x;
end

void main()
begin
    print recursive(1);
end