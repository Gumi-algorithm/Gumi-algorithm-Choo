#include<iostream>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;
	cin >> n;
	/*
	int dp[1001];
	dp[0] = 0;
	dp[1] = 1;
	dp[2] = 2;


	for (int i = 3; i <= n; i++) {
		dp[i] = (dp[i - 1] + dp[i - 2])%10007;
	}
	cout << dp[n];
	*/
	
	int a = 1;
	int b = 1;
	int tmp;
	for (int i = 0; i < n - 1; i++) {
		tmp = (a + b)% 10007;
		a = b;
		b = tmp;
	}
	cout << b;
	
	return 0;
}