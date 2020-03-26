from sys import stdin
from decimal import Decimal
import math

input = stdin.readline
x0,y0,rad = map(int,input().split())
n=int(input())
massive = list(map(int,input().split()))
Poly = lambda x: sum((massive[i]*(x**(n-i)) for i in range(n+1)))

x=int(input())
ESP = Decimal(1e-9)

def rass( x, y):
  return math.sqrt((x0-x)**2+(y0-y)**2)

def bisect():
  l = x
  r = x0-rad
  res=rass(x, Poly(x))
  while( abs(rad-res) > ESP):
    mid = (l+r)/2
    res=rass(mid, Poly(mid))
    # print(res)
    if(rad-res>0):
      l = mid
    else:
      r = mid
  return (l+r)/2
print(bisect())
