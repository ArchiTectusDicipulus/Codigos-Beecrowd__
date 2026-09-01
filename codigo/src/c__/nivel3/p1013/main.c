#include <stdio.h>
#include<stdlib.h>
int main() {
	int a,b,c,d,MaiorAB;
	scanf("%d%d%d",&a,&b,&c);
	d=(a+b+abs(a-b))/2;
	MaiorAB=(d+c+abs(d-c))/2;
	printf("%d eh o maior\n",MaiorAB);
	return 0;
}
