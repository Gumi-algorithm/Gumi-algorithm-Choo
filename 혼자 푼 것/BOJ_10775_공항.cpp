#include<iostream>

using namespace std;

int union_find[100001];

int find(int num) {
	if (union_find[num] == num)
		return num;

	return union_find[num] = find(union_find[num]);
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int g, p;

	cin >> g >> p;

	

	//���Ͽ����ε� �ʱ�ȭ
	for (int i = 0; i <= g; i++) {
		union_find[i] = i;
	}
	int ans = 0;

	//4�� ���� ������ �� �ٷ� �Ʒ� ������ �����Ѽ� ������������ �Ѱ���
	for (int i = 0; i < p; i++) {
		int now;
		cin >> now;
		
		int pn = find(now);
		if (pn - 1 < 0)
			break;
		union_find[pn] = pn - 1;
		ans++;

	}
	cout <<ans<<'\n';

	return 0;
}