#include<stdio.h>
#include<cstring>
#include<iostream>
#include <fstream>
using namespace std;
int dp[1000][1000],pages[1000],n,m;
int MAX = 1000000000;

int rec(int position , int sub ) {
	if(position > n) {
		if(sub == 0)return 0;
		return MAX;
	}
	if(sub == 0) {
		if(position > n)return 0;
		return MAX;
	}

	if(dp[position][sub] != -1)return dp[position][sub];

	int ans = MAX,i,sum = 0;
	for(i = position; i <= n; i++) {
		sum += pages[i];
		ans = min(ans,max(sum,rec(i+1,sub-1)));
	}
	dp[position][sub]=ans;
	return ans;
}

int main() {
	ofstream file;
    file.open("SalidaDinamica.txt");
	ifstream fe("Prueba5.txt");
	fe >> n;
	fe >> m;
	for(int i = 0 ; i < n; i++) {
		fe >> pages[i];						
	}
	int i,aux,ans,pos,sum;
	memset(dp,-1,sizeof(dp));
	
	int respuesta = rec(0,m);
	file << respuesta;
	file << "\n";
	cout << respuesta <<endl;

	while( aux <= n ) {
		ans = MAX,pos=-1,sum=0;
		for(i = aux; i <= n; i++) {
			sum += pages[i];
			if(ans > max(sum ,rec(i+1,m-1))) {
				ans = max(sum,rec(i+1,m-1));
				pos = i;
			}
		}
		m--;
		for(i = aux; i <= pos; i++) {
			if(pages[i]==0){
			
				return 0;
			}
			file << pages[i];
			file << " ";
			cout << pages[i] << " ";
		}
		file << " / ";
		cout << " / ";
		aux = pos+1;
	}

	return 0;
}
