# Максимальная сумма
#
# В этой задаче вам требуется найти непустой отрезок массива с максимальной суммой.
#
# Входные данные
#
# В первой строке входных данных записано единственное число n (1≤n≤3⋅10^5) -  размер массива.
#
# Во второй строке записано n целых чисел a_i(-10^9 ≤a_i≤10^9) - сам массив.
#
# Выходные данные
#
# Выведите одно число - максимальную сумму на отрезке в данном массиве.
#
# Sample Input 1:
#
# 4
# 1 2 3 4
# Sample Output 1:
#
# 10
# Sample Input 2:
#
# 4
# 5 4 -10 4
# Sample Output 2:
#
# 9

import sys
input = sys.stdin.readline
n = int(input())

accum = 0
ans = -1000000000
for i,e in enumerate((int(x) for x in input().split()),start=0):
    accum += e
    ans = max(accum,ans)
    accum = max(accum,0)


print(ans, sep="\n")
