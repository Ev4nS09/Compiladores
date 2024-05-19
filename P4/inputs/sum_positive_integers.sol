int sum_positive_integers(int x)
begin
    int result;

    if x < 0 then
        result = 0;
    else
        return sum_positive_integers(x - 1) + x;

    return result;
end

void main()
begin
    print sum_positive_integers(7);
end
