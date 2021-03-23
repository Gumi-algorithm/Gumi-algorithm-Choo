#include<iostream>
#include<cmath>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;
	cin >> n;

	int arr[1001][3];
	int mem[2][3];
	int a, b, c;
	int t = 0;
	int ans = 1000001;
	for (int i = 0; i < n; i++) {
		cin >> arr[i][0] >> arr[i][1] >> arr[i][2];
	}

	for (int i = 0; i < 3; i++) {
		//ù��° �� ������ �Ѱ��� �����صΰ� 3���� ��� ������
		//ù��° �� �ʱ�ȭ
		for (int j = 0; j < 3; j++) {
			if (i == j)
				mem[0][j] = arr[0][j];
			else
				mem[0][j] = 1000001;
		}
		t = 1;
		for (int j = 1; j < n; j++) {
			mem[t][0] = min(mem[1 - t][1], mem[1 - t][2]) + arr[j][0];
			mem[t][1] = min(mem[1 - t][0], mem[1 - t][2]) + arr[j][1];
			mem[t][2] = min(mem[1 - t][0], mem[1 - t][1]) + arr[j][2];

			t = 1 - t;
		}

		//���������� ù��° ���̶� �� ������ �Ѱܹ���
		for (int j = 0; j < 3; j++) {
			if (i == j)
				continue;
			ans = min(ans, mem[1 - t][j]);
		}
	}
	cout << ans;

	return 0;
}