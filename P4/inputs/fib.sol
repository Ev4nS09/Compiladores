int x = 2;

int fib(int n)
begin
    int previous = 0;
    int result = 1;
    int i, temp;

    if n == 0 then
        result = previous;
    else
        for i = 1 to n - 1 do
        begin
            temp = result;
            result = previous + result;
            previous = temp;
        end

    return result;
end

void main()
begin
    int result = fib(20);

    print result;
    print fib(46);
end