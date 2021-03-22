#include<iostream>
#include<vector>
#include<cmath>

using namespace std;

vector<pair<int,int> > arr[1001];
int dist[1001];//�� �ε��������� �ּҺ��(�̰� �켱����ť�� �ٲٸ� nlogn��)
bool isvisited[1001];//�湮üũ
int n, m;

int prim() {
	int ans = 0;

	//�ʱ�ȭ
	for (int i = 0; i <= n; i++) {
		dist[i] = 210000000;
		isvisited[i] = false;
	}

	dist[1] = 0;//1���� ������������ ����
	for (int i = 1; i <= n; i++) {
		int now = -1;
		int nowlength = 210000000;
		//���� ����� �����߿� ���� ����ġ�� ���� ���� ���
		for (int j = 1; j <= n; j++) {
			if (!isvisited[j] && nowlength > dist[j]) {
				now = j;
				nowlength = dist[j];
			}
		}
		if (now < 0) {
			//���õ� ������ ������ Ż��
			return ans;
		}
		//������ ���õǸ� �ش� ������ �̾����� ������ �湮ó����
		isvisited[now] = true;
		ans += nowlength;//���õ� ������ ����ġ��ŭ ���ϰ��� ��������

		//�߰��� ������ ����� ������������ ���� ������� ���Ͽ� dist�� ������
		for (int j = 0; j < arr[now].size(); j++) {
			int vertex = arr[now][j].first;
			int weight = arr[now][j].second;
			dist[vertex] = min(dist[vertex], weight);
		}
	}
	return ans;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;

	for (int i = 0; i < m; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		
		arr[a].push_back(make_pair(b, c));
		arr[b].push_back(make_pair(a, c));
	}
	int ans = prim();
	cout<<ans;
}