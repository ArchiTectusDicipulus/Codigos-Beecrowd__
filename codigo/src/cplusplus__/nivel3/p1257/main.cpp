#include <iostream>
#include <string>
#include <vector>

using namespace std;

class CALCULATOR {
private:
    vector<vector<string>> cases_list;

    int calculateResults(int j, int k, char c) {
        if ( c >= 'a' && c <= 'z' ){ c = c - 'a' + 'A'; }
        return c - 'A' + j + k;
    }

    void setCases() {
        int n_cases;
        cin >> n_cases;

        for (int i = 0; i < n_cases; i++) {
            int n_lines;
            cin >> n_lines;

            vector<string> current_case;

            for (int j = 0; j < n_lines; j++) {
                string temp;
                cin >> temp;
                current_case.push_back(temp);
            }

            cases_list.push_back(current_case);
        }
    }

    void printResults() {
        for (int i = 0; i < cases_list.size(); i++) {
            int value = 0;

            for (int j = 0; j < cases_list.at(i).size(); j++) {
                string temp = cases_list.at(i).at(j);

                for (int k = 0; k < temp.size(); k++) {
                    value += calculateResults(j, k, temp.at(k));
                }
            }

            cout << value << endl;
        }
    }

public:
    void manager_work() {
        setCases();
        printResults();
    }
};

int main() {
    CALCULATOR string_calculator;
    string_calculator.manager_work();

    return 0;
}
