#include<stdio.h>
int main(){
	int i,u;
	scanf("%d",&i);
	for(u=0;u<=10000;u++){
		if(u%i==2){
			printf("%d\n",u);

		}


	}
	return 0;
}
