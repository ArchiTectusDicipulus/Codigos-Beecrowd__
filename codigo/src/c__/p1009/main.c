#include <stdio.h>
int main() {
	float total,a,b;
	char nome[16];
	scanf("%s%f%f",&nome,&a,&b);
	total=a+(b*15/100);
	printf("TOTAL = R$ %.2f\n",total);
	return 0;
}
