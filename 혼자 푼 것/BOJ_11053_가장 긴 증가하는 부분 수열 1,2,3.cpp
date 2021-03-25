#include<iostream>

using namespace std;

int mem[1000001];
int _lower_bound(int start, int end, int val) {

	while (start < end) {
		int mid = (start + end) / 2;
		if (mem[mid] >= val)
			end = mid;
		else
			start = mid + 1;
	}
	return end;
}

int main() {

	int n;
	cin >> n;


	for (int i = 0; i < n; i++) {
		cin >> mem[i];
	}

	int ans = 1;
	for (int i = 1; i < n; i++) {
		if (mem[i] > mem[ans - 1]) {
			mem[ans++] = mem[i];
		}
		else {
			int idx = _lower_bound(0, ans, mem[i]);
			mem[idx] = mem[i];
		}
	}
	cout << ans;


	return 0;
}