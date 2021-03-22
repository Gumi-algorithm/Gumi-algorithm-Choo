#include<iostream>
#include<algorithm>
#include<vector>

using namespace std;

struct node{
	int x, y, w;
};

long long parent[100001][2];//0: 부모 1:유니온의 노드갯수
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
	
	//유니온 초기화
	for (int i = 0; i <= n; i++) {
		parent[i][0] = i;
		parent[i][1] = 1;//처음엔 자기혼자니까 1
	}

	//내림차순 정렬
	sort(arr.begin(), arr.end(),comp);

	long long ans = 0;

	int size = arr.size();
	for (int i = 0; i < size; i++) {
		int a = arr[i].x;
		int b = arr[i].y;
		long long c = arr[i].w;//가중치

		int pa = find(a);
		int pb = find(b);

		if (pa != pb) {
			//일단 a유니온 갯수 * b유니온 갯수 * 두 노드를 끊을때 필요한 모든 간선의 합(내림차순이니까 전체간선에서 현재간선 빼가면됨)
			//하면 현재 간선을 끊으면 영향받는 노드들의 가중치가 나옴
			ans += (((parent[pa][1] * parent[pb][1]) % 1000000000) * edge) % 1000000000;
			ans %= 1000000000;

		
			//그리고 두 유니온을 합쳐 (유니온 갯수도 같이 갱신)
			parent[pb][0] = pa;
			parent[pa][1] += parent[pb][1];
		}
		//제일 높은 간선 썻으니까 다음꺼는 한단계 낮은 간선이니 빼줘
		edge -= c;
	}

	cout << ans;

	return 0;
}
/*
간선을 내림차순 정렬해 높은간선부터 합침
6 7
1 2 10
2 3 2
4 3 5
6 3 15
3 5 4
4 5 3
2 6 6

제일 먼저 가중치가 15인 간선이 옴
그럼 모든 간선이 지워지고 현재 가중치 15인 간선만 남았다고 생각해
그럼 6 3이 안이어지게 하려면 모든 간선을 지우고 15인 간선도 지워야댐
그리고 6 3 유니온시켜(합쳐)

다음은 가중치가 10인 간선이 옴
이것도 위랑 똑같이 1 2 를 안이어지게 하려면 10이하인거 다지우고 10도 지워야됨
그리고 1 2 유니온시켜

다음으로 가중치가 6인 간선이 옴
2 6인데 위에서 (1,2) (3,6) 유니온해서 묶었으니 1 3 / 1 6 / 2 3 / 2 6을 안이어지게 하려면
6이하인걸 다 지우고 6도 지워야 되는거임
그니까 (2+3+4+5+6)*2*2 인거임

이런식으로 반복하면됨
*/