#include<iostream>
#include<algorithm>
#include<vector>

using namespace std;

struct node{
	int x, y, w;
};

long long parent[100001][2];//0: �θ� 1:���Ͽ��� ��尹��
vector<node> arr;

bool comp(node a,node b) {
	return a.w > b.w;
}

int find(int num) {
	if (parent[num][0] == num)
		return num;
	return parent[num][0] = find(parent[num][0]);
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n, m;
	cin >> n >> m;


	
	long long edge = 0;

	for (int i = 0; i < m; i++) {
		int a, b, c;
		cin >> a>>b>>c;
		arr.push_back({ a,b,c });
		edge += c;
	}
	
	//���Ͽ� �ʱ�ȭ
	for (int i = 0; i <= n; i++) {
		parent[i][0] = i;
		parent[i][1] = 1;//ó���� �ڱ�ȥ�ڴϱ� 1
	}

	//�������� ����
	sort(arr.begin(), arr.end(),comp);

	long long ans = 0;

	int size = arr.size();
	for (int i = 0; i < size; i++) {
		int a = arr[i].x;
		int b = arr[i].y;
		long long c = arr[i].w;//����ġ

		int pa = find(a);
		int pb = find(b);

		if (pa != pb) {
			//�ϴ� a���Ͽ� ���� * b���Ͽ� ���� * �� ��带 ������ �ʿ��� ��� ������ ��(���������̴ϱ� ��ü�������� ���簣�� �������)
			//�ϸ� ���� ������ ������ ����޴� ������ ����ġ�� ����
			ans += (((parent[pa][1] * parent[pb][1]) % 1000000000) * edge) % 1000000000;
			ans %= 1000000000;

		
			//�׸��� �� ���Ͽ��� ���� (���Ͽ� ������ ���� ����)
			parent[pb][0] = pa;
			parent[pa][1] += parent[pb][1];
		}
		//���� ���� ���� �����ϱ� �������� �Ѵܰ� ���� �����̴� ����
		edge -= c;
	}

	cout << ans;

	return 0;
}
/*
������ �������� ������ ������������ ��ħ
6 7
1 2 10
2 3 2
4 3 5
6 3 15
3 5 4
4 5 3
2 6 6

���� ���� ����ġ�� 15�� ������ ��
�׷� ��� ������ �������� ���� ����ġ 15�� ������ ���Ҵٰ� ������
�׷� 6 3�� ���̾����� �Ϸ��� ��� ������ ����� 15�� ������ �����ߴ�
�׸��� 6 3 ���Ͽ½���(����)

������ ����ġ�� 10�� ������ ��
�̰͵� ���� �Ȱ��� 1 2 �� ���̾����� �Ϸ��� 10�����ΰ� ������� 10�� �����ߵ�
�׸��� 1 2 ���Ͽ½���

�������� ����ġ�� 6�� ������ ��
2 6�ε� ������ (1,2) (3,6) ���Ͽ��ؼ� �������� 1 3 / 1 6 / 2 3 / 2 6�� ���̾����� �Ϸ���
6�����ΰ� �� ����� 6�� ������ �Ǵ°���
�״ϱ� (2+3+4+5+6)*2*2 �ΰ���

�̷������� �ݺ��ϸ��
*/