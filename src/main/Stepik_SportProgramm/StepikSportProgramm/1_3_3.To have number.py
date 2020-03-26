# Есть ли число?
#
# В этой задаче вам надо будет много раз проверять, что число есть в данном массиве.
#
# Входные данные
#
# В первой строке записаны два целых числа n, m (1 ≤ n,m ≤ 2⋅10^5) - размер массива и количество запросов соответственно.
#
# В следующей строке записано n различных целых чисел, каждое по модулю не превосходит 10^9
#  . Гарантируется, что числа в массиве идут в порядке возрастания.
#
# Далее в m строках записано по одному целому числу, также по модулю не превосходящего 10^9
#  . Вам требуется проверить, есть ли оно в массиве.
#
# Выходные данные
#
# Для каждого запроса в новой строке выведите строку YES, если очередное число есть в массиве,
# и NO иначе.
#
# Sample Input:
#
# 5 10
# -11 -10 -2 11 18
# -10
# -11
# -11
# 18
# 12
# 18
# 11
# -15
# 4
# -1
# Sample Output:
#
# YES
# YES
# YES
# YES
# NO
# YES
# YES
# NO
# NO
# NO

import sys
input = sys.stdin.readline
n, m = map(int, input().split())
a = list(map(int, input().split()))
for i in range(m):
    s=int(input())
    l = -1
    r = n
    print(*a)
    while(l+1<r):
        mid=(l+r)//2
        print('mid:'+str(mid))
        if(a[mid]<s):
            l=mid
        else:
            r=mid
    if(r<n)and(a[r]==s): # в питоне похоже не сокращают вычисление логических выражений ибо если условие r>=n, то индекс будет за пределами массива
        print('YES')
    else:
        print('NO')