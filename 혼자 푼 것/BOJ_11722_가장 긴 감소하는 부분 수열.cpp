#include<iostream>

using namespace std;

int mem[1001];
int _upper_bound(int start,int end, int val) {

	while (start < end) {
		int mid = (start + end) / 2;
		if (mem[mid] <= val)
			end = mid;
		else
			start = mid + 1;
	}
	return end;
}

int main() {

	int n;
	cin >> n;

	int arr[1001];
	
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}

	mem[0] = arr[0];
	int ans = 1;
	for (int i = 1; i < n; i++) {
		if (arr[i] < mem[ans - 1]) {
			mem[ans++] = arr[i];
		}
		else {
			int idx = _upper_bound(0, ans, arr[i]);
			mem[idx] = arr[i];
		}
	}
	cout << ans;


	return 0;
}