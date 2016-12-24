#include <iostream>
#include <cstdio>
#include <cmath>
#include <fstream>

using namespace std;

int sizes[] = {25, 35, 50, 60, 75, 90, 100, 130, 170, 200};

void generate(const string& out_file, int n) {
  ofstream out(out_file);

  while (sizes[n]--) {
    int x = rand() % 10000;
    int y = rand() % 10000;
    double xd = x / 1000.0;
    double yd = y / 1000.0;
    out << xd << " " << yd << "\n";
  }
  out.close();
}

int main() {
  srand(time(NULL));
  for (int i = 0; i < 10; i++) {
    generate("input_" + std::to_string(i) + ".txt", i);
  }
  return 0;
}
