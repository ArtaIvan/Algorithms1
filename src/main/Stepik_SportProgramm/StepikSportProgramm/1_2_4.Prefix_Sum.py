# Префиксные суммы
#
# В этой задаче вам нужно будет много раз отвечать на запрос <<Найдите сумму чисел на отрезке в массиве>>.
#
# Входные данные
#
# В первой строке записано два целых числа n и q (1≤n,q ≤3⋅10^5) - размер массива и количество запросов.
#
# Во второй строке записаны n целых чисел a_i
# ​(1≤a_i≤10^9) - сам массив.
#
# Далее в q строках описаны запросы к массиву. Каждый запрос описывается двумя числами
# l, r (1≤l≤r≤n) - левой и правой границей отрезка, на котором нужно найти сумму.
#
# Выходные данные
#
# Для каждого запроса в отдельной строке выведите единственное число - сумму на соответствующем отрезке.
#
# Sample Input:
#
# 4 10
# 1 2 3 4
# 1 1
# 1 2
# 1 3
# 1 4
# 2 2
# 2 3
# 2 4
# 3 3
# 3 4
# 4 4
# Sample Output:
#
# 1
# 3
# 6
# 10
# 2
# 5
# 9
# 3
# 7
# 4


import sys
input = sys.stdin.readline
n, q = map(int, input().split())
accum = 0
pref_sum = [0] * ( n+1)

for i,e in enumerate((int(x) for x in input().split()),start=1):
    accum += e
    pref_sum[i] = accum
ans = [0] * q
for i in range(q):
    l, r = map(int, input().split())
    ans[i] = pref_sum[r]-pref_sum[l-1]

print(*ans, sep="\n")
