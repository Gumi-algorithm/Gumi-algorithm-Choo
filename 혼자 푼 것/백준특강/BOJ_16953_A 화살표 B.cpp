#include<iostream>
#include<queue>
#include<math.h>

using namespace std;

//���ٲ����̶� �Ȱ��� ����
int main() {

	int a, b;

	cin >> a >> b;

	//�ִܽð����ϴ°Ŵϱ� BFS
	queue<pair<long long,int> > q;//����, ����ġ
	q.push(make_pair(a,1));
	int ans = -1;

	int div = b / a + 1;
	int maxweight = sqrt(div)+1;//a�� 2�� ��� ���ϸ� b���� Ŀ���°�
	while (!q.empty()){
		pair<long long, int> tmp= q.front();
		q.pop();

		//�� ã������
		if (tmp.first == b) {
			ans = tmp.second;
			break;
		}

		//���� 1 ���̴°ź��� 2 ���ϴ°� ���� �����ε� 
		//���������� ã�°��� �Ѿ���� ��ã�°���
//		if (maxweight<tmp.second) {
//			ans = -1;
//			break;
//		}

		//�� ū ���� ������ ���̻� �񱳾��ص���
		if (tmp.first > b)
			continue;


		q.push(make_pair(tmp.first * 2, tmp.second + 1));
		q.push(make_pair(tmp.first * 10 + 1, tmp.second + 1));
	}
	cout << ans;
	return 0;
}