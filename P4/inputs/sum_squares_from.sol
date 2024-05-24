real sum_s(real x, real y, real z)
begin
   if y == 0 then
        return z;
   else
    return z + sum_s(x, y - 1, (x+y-1)*(x+y-1));
end

void main()
begin
    print sum_s(6, 2, 0);
end