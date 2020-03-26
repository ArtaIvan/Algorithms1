def checkYear(year):
    strY=str(year)
    setY=set(strY)
    if len(strY)!=len(setY):
        return False
    if('2'in setY):
        return False
    if('0'in setY):
        return False
    return True

# input = sys.stdin.readline
n = int(input())
n+=1
while((not checkYear(n))):
    # Это бредовое решение
    # по- правильному надо делать цикл,
    # в котором генерируется числа из X разных цифр  путем перестановок от большего к меньшему. X<9

    if(n>19876543)&(n<31456789):
        n=31456788
    if(n>31987654)&(n<34156789):    
        n=34156788
    if(n>49876531)&(n<51346789):    
        n=51346788
    if(n>59876431)&(n<61345789):    
        n=61345788
    if(n>69875431)&(n<71345689):    
        n=71345688
    if(n>79865431)&(n<81345679):    
        n=81345678    
    if(n>89765431)&(n<91345678):    
        n=91345677    
    if(n>98765431):
        n=-1
        break
    n+=1    
print(n)
