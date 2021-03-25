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
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;
	cin >> n;


	for (int i = 0; i < n; i++) {
		cin >> mem[i];
	}
	int tracking[1000001][2];
	int ans = 1;
	tracking[0][0] = 0;//ÀÎµ¦½º
	tracking[0][1] = mem[0];//°ª
	for (int i = 1; i < n; i++) {
		if (mem[i] > mem[ans - 1]) {
			mem[ans++] = mem[i];
			tracking[i][0] = ans-1;
			tracking[i][1] = mem[i];
		}
		else {
			int idx = _lower_bound(0, ans, mem[i]);
			mem[idx] = mem[i];
			tracking[i][0] = idx;
			tracking[i][1] = mem[i];
		}
	}
	int tmp = ans - 1;
	for (int i = n-1; i >= 0; i--) {
		if (tracking[i][0] == tmp) {
			mem[tmp] = tracking[i][1];
			tmp--;
		}
	}

	cout << ans<<"\n";
	for (int i = 0; i < ans; i++) {
		cout << mem[i] << " ";
	}
	return 0;
}