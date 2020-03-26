import sys
input = sys.stdin.readline
n, m = map(int, input().split())
a = list(map(int, input().split()))
for i in range(m):
    s=int(input())
    l = -1
    r = n
    while(l+1<r):
        mid=(l+r)//2
        if(a[mid]<s):
            l=mid
        else:
            r=mid
    if(r<n):
        print(a[r])
    else:
        print('NO SOLUTION')
