#include<iostream>

using namespace std;

int arr[201];

//��ξ��� ���
int find(int num) {
	if (arr[num] == num)
		return num;
	return arr[num] = find(arr[num]);
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;//�����Ǽ�
	cin >> n;

	int m;//�����ȹ�� ���� ���õ��� ��
	cin >> m;



	//union_find�ʱ�ȭ
	for (int i = 0; i <= n; i++) {
		arr[i] = i;
	}

	//���ô� ���� ����Ǵ°��̱⶧���� �������ð� �������ø� ����Ű����
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			int tmp;
			cin >> tmp;
			if (i < j && tmp==1) {
				int a = find(i);
				int b = find(j);
				//���� �θ� �ƴϸ� �ٽ�������
				if (a != b) {
					arr[b]=a;
				}
			}
		}
	}
	int now = -1;
	int answer = 0;
	for (int i = 0; i < m; i++) {
		int tmp;
		cin >> tmp;
		if (now == -1) {
			now = find(tmp);
		}
		else {
			//�θ� �ٸ��� ������ �ȵǾ������� �������
			if (now != find(tmp)) {
				answer = 1;
				break;
			}
		}
	}

	if (answer == 1)
		cout << "NO";
	else
		cout << "YES";


	return 0;
}