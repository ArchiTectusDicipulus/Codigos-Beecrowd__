#include<stdio.h>
int main(){
	int a=1,b=2,c;
	scanf("%d",&c);
	if(c==1){
		printf("0");
		c--;
	}else{
		printf("0 1");
		c+=-2;
	}
	while(c>0){
		if(c>0){
			printf(" %d",a);
		}
		--c;
		a+=b;
		if(c>0){
			printf(" %d",b);
		}
		--c;
		b+=a;
	}printf("\n");
	return 0;
}
