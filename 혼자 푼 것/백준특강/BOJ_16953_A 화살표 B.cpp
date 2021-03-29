#include<iostream>
#include<queue>
#include<math.h>

using namespace std;

//숨바꼭질이랑 똑같은 문제
int main() {

	int a, b;

	cin >> a >> b;

	//최단시간구하는거니까 BFS
	queue<pair<long long,int> > q;//숫자, 가중치
	q.push(make_pair(a,1));
	int ans = -1;

	int div = b / a + 1;
	int maxweight = sqrt(div)+1;//a에 2를 몇번 곱하면 b보다 커지는가
	while (!q.empty()){
		pair<long long, int> tmp= q.front();
		q.pop();

		//답 찾았을때
		if (tmp.first == b) {
			ans = tmp.second;
			break;
		}

		//옆에 1 붙이는거보다 2 곱하는게 작은 연산인데 
		//작은연산이 찾는값을 넘어갓으니 못찾는거임
//		if (maxweight<tmp.second) {
//			ans = -1;
//			break;
//		}

		//더 큰 값이 들어오면 더이상 비교안해도됨
		if (tmp.first > b)
			continue;


		q.push(make_pair(tmp.first * 2, tmp.second + 1));
		q.push(make_pair(tmp.first * 10 + 1, tmp.second + 1));
	}
	cout << ans;
	return 0;
}