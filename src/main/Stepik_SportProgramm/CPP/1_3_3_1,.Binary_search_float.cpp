#include <iostream>
using namespace std;

const int N = 10;
const long double EPS = 1e-9;
int a[N];
long double y[N];

int n;



long double f(long double x){
		y[0] = 1;
        y[1]=  x;
		y[2] = x*x;
		y[3] = y[2]*x;
		y[4] = y[2]*y[2];
		y[5] = y[4]*x;
	
	long double res=0.0;
	for(int i = n; i >= 0; i--){
		res=res+y[i]*a[i];	
	}	
	return res;
}

long double findRoot(){
    long double l = -50.0;
    long double r = 50.0;
    //for(int i=0;i<=100;i++){
    while( r-l > EPS){    
        long double mid = (l+r)/2;
        if(f(mid)<0.0)
            l =mid;
        else 
            r = mid;
    }
    return (l+r)/2.0;    
}
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
	cout.precision(16);
	
    cin>>n;
    for(int i = n; i >= 0; i--){
        cin>>a[i];
    }
   
    long double  res;
        
    res=findRoot();
    cout<<res<<endl;            

    return 0;
}