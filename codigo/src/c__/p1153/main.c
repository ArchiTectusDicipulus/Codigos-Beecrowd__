#include<stdio.h>
int main(){
	int a,n,s=1;
	scanf("%d",&a);
	for(n=0;(a-n)>1;n++){
		s=(a-n)*s;
	}
	printf("%d\n",s);
	return 0;
}
