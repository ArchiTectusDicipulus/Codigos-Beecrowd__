#include<stdio.h>
int main(){
	int a,b,c,d,e;
	scanf("%d",&a);
	for(e=0,b=1,c=1,d=1;e<a;e++,b++){
		printf("%d %d %d\n%d %d %d\n",b,b*b,b*b*b,b,(b*b)+1,(b*b*b)+1);
	}
	return 0;
}
