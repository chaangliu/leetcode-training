#include <string>
#include <iostream>
#include <stack>
using namespace std;

// my attemp:

class Solution {
public:
    string reversePrefix(string word, char ch) {
      stack<char> stack;
      string res;
      bool found = false;
      for (char c : word) {
        if (!found) {
          if (c != ch) {
            stack.push(c);
          } else {
            found = true;
            res.push_back(ch);
            while (!stack.empty()) {
              char c = stack.top();
              stack.pop();
              res.push_back(c);
            }
          }
        } else {
          res.push_back(c);
        }
      }
      return !found ? word : res; 
    }
};

int main() {
  Solution* s = new Solution();
  string res = s->reversePrefix("abcd", 'b');
  cout << res;
}

// official solution:

class Solution {
public:
    string reversePrefix(string word, char ch) {
        int index = word.find(ch);
        if (index != string::npos) {
            reverse(word.begin(), word.begin() + index + 1);
        }
        return word;
    }
};
