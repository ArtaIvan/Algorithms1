import sys
input = sys.stdin.readline
n, m = map(int, input().split())


for i in range(m):
    l = -1
    r = n
    k=int(input())
    print('k:'+str(k))
    step=0
    while(l+1<r):
        mid=(l+r)//2
        print('mid:'+str(mid))
        if(mid<k):
            l=mid
        else:
            r=mid
        step+=1
    print(step, sep="\n")
