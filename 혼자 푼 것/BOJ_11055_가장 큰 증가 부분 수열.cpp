#include<iostream>

using namespace std;

int main() {
	
	int n;
	cin >> n;

	int arr[1001];
	int mem[1001];
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
		mem[i] = arr[i];//mem√ ±‚»≠
	}
	
	//n^2
	int ans = mem[0];
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < i; j++) {
			if (arr[j] < arr[i]) {
				mem[i] = mem[i] < mem[j] + arr[i] ? mem[j]+arr[i] : mem[i] ;
				ans = ans < mem[i] ? mem[i] : ans;
			}
		}
	}
	cout << ans;


	return 0;
}