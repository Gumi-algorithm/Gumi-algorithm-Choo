#include<iostream>
#include<map>
#include<sstream>

using namespace std;

pair<int, int> union_find[200002];//idx, cnt

int find(int num) {
	if (num == union_find[num].first)
		return num;

	return union_find[num].first = find(union_find[num].first);
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int t;
	cin >> t;

	map<string, int> m;
	int midx;
	while (t--> 0) {
		int f;
		cin >> f;
		midx = 0;

		for (int i = 0; i <= f * 2; i++) {
			union_find[i] = make_pair(i, 1);
		}

		for (int i = 0; i < f; i++) {
			string tmp1,tmp2;
			cin >> tmp1>>tmp2;
			//없으면 삽입
			if(m.find(tmp1) == m.end())
				m.insert(make_pair(tmp1, midx++));
			if(m.find(tmp2) == m.end())
				m.insert(make_pair(tmp2, midx++));
			
			int a = find(m[tmp1]);
			int b = find(m[tmp2]);
			if (a != b) {		
				union_find[b].first = union_find[a].first;
				union_find[a].second += union_find[b].second;
			}
			cout << union_find[a].second << "\n";
		}
		m.clear();
	}
	return 0;
}