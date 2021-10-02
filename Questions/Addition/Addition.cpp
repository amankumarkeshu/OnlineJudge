#include <bits/stdc++.h>
using namespace std;

class Addition {
public:
    int add(int a, int b) {
        int res = 0;
        // CRIO_SOLUTION_START_MODULE_L1_PROBLEMS
        res = a + b;
        // CRIO_SOLUTION_END_MODULE_L1_PROBLEMS
        return res;
    }
};

int main() {
    int a, b;
    cin >> a >> b;
    int result = Addition().add(a, b);
    cout << result << endl;
}
