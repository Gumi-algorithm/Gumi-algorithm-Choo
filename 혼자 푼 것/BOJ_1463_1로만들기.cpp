#include<iostream>
#include<cmath>

using namespace std;

int main() {

	int n;
	int mem[1000001];
	mem[1] = 0;
	mem[2] = 1;

	cin >> n;

	for(int i=3;i<=n;i++){
		int tmp = -1;
		if (i % 3 == 0) {
			if (tmp == -1)
				tmp = mem[i / 3];
			else
				tmp = min(tmp, mem[i / 3]);
		}if (i % 2 == 0) {
			if (tmp == -1)
				tmp = mem[i / 2];
			else
				tmp = min(tmp, mem[i / 2]);
		}
		if (tmp == -1)
			tmp = mem[i - 1];
		else
			tmp = min(tmp, mem[i -1]);
		mem[i] = 1 + tmp;
	}

	cout << mem[n];

	return 0;
}