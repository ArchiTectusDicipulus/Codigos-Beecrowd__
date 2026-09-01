#include<stdio.h>
int main(){
	int i[20],aux,a=0;
	for(a=0;a<20;a++){
		scanf("%d",&i[a]);
	}a=0;
	while(a<10){
		aux=i[a];
		i[a]=i[19-a];
		i[19-a]=aux;
		a++;
	}a=0;
	for(a=0;a<20;a++){
		printf("N[%d] = %d\n",a,i[a]);
	}
	return 0;
}
