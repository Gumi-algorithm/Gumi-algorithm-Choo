#include<iostream>
#include<vector>
#include<cmath>

using namespace std;

vector<pair<int,int> > arr[1001];
int dist[1001];//각 인덱스까지의 최소비용(이걸 우선순위큐로 바꾸면 nlogn됨)
bool isvisited[1001];//방문체크
int n, m;

int prim() {
	int ans = 0;

	//초기화
	for (int i = 0; i <= n; i++) {
		dist[i] = 210000000;
		isvisited[i] = false;
	}

	dist[1] = 0;//1번을 시작지점으로 잡음
	for (int i = 1; i <= n; i++) {
		int now = -1;
		int nowlength = 210000000;
		//현재 연결된 간선중에 가장 가중치가 적은 간선 골라
		for (int j = 1; j <= n; j++) {
			if (!isvisited[j] && nowlength > dist[j]) {
				now = j;
				nowlength = dist[j];
			}
		}
		if (now < 0) {
			//선택된 간선이 없으면 탈출
			return ans;
		}
		//간선이 선택되면 해당 간선과 이어지는 정점을 방문처리해
		isvisited[now] = true;
		ans += nowlength;//선택된 간선의 가중치만큼 리턴값에 누적시켜

		//추가된 정점과 연결된 간선정보들을 기존 간선들과 비교하여 dist에 저장해
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