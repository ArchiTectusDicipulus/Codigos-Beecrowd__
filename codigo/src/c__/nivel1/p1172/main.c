#include<stdio.h>
int main(){
	int i[12],a=0;
	for(a=0;a<10;a++){
		scanf("%d",&i[a]);
		if(i[a]<=0){
			i[a]=1;
		}
	}a=0;
	for(a=0;a<10;a++){
		printf("X[%d] = %d\n",a,i[a]);
	}
	return 0;
}
