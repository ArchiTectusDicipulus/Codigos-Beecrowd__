#include<stdio.h>
#include<string.h>
int main(){
	int n,x[10],i;
	scanf("%d",&n);
	for(i=0;i<10;i++){
		x[i] = n;
		printf("N[%d] = %d\n",i,n);
		n*= 2;
	}
	return 0;
}
