#include<stdio.h>
int main(){
	int a,d;
	scanf("%d",&a);
	for(d=1;d<(a+1);d++){
		if(a%d==0){
			printf("%d\n",d);
		}
	}
}
