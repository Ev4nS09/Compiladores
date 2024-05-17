string posicao;
int i = 0;

string change(int i)
begin
string posicao;
    if i == 0 then
        posicao = "N";
    if i == 1 then
        posicao = "E";
    if i == 2 then
        posicao = "S";
    if i == 3 then
        posicao = "W";

    return posicao;
end


void main()
begin
    while i < 5 do
    begin
        posicao = change(i);
        i = i + 1;
        print posicao;
    end
    print "Acabou";
end