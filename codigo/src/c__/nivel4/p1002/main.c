#include <stdio.h>
#include<math.h>
int main() {
	double raio,circo;
	scanf("%lf",&raio);
	circo=pow(raio,2)*3.14159;
	printf("A=%.4lf\n",circo);
	return 0;
}
