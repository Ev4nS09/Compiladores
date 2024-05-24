bool isPalindromeNumber(int x)
begin
    string originalNumber = x + "";
    string reversedNumber = "";

    while x > 0 do
    begin
        reversedNumber = reversedNumber + (x % 10) + "";
        x = x / 10;
    end

    return reversedNumber == originalNumber;
end

void main()
begin
    print isPalindromeNumber(4353);
end