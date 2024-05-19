int x = 2;
real y = 3;
string z = "z";
bool b = true;




void main()
begin
    x = y;  //Error because a real number cannot be converted to an integer
    y = x;  //not error because a real number can be converted to an integer

    begin
    int x = 3;
    int x = 4;
        begin
            begin
            int x = 2;
                begin
                    begin
                        int scope1 = scope2;
                    end
                end
            end
        end
    end


    begin
    int scope2;
        begin
            begin
                begin
                    begin
                        scope2 = scope1;
                    end
                end
            end
        end
    end
end