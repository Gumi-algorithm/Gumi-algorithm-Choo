#include<iostream>

using namespace std;

int arr[201];

//경로압축 사용
int find(int num) {
	if (arr[num] == num)
		return num;
	return arr[num] = find(arr[num]);
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;//도시의수
	cin >> n;

	int m;//여행계획에 속한 도시들의 수
	cin >> m;



	//union_find초기화
	for (int i = 0; i <= n; i++) {
		arr[i] = i;
	}

	//도시는 서로 연결되는것이기때문에 높은도시가 작은도시를 가르키게함
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			int tmp;
			cin >> tmp;
			if (i < j && tmp==1) {
				int a = find(i);
				int b = find(j);
				//공통 부모가 아니면 다시합쳐줘
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
			//부모가 다르면 연결이 안되어있으니 여행못함
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