#include <stdio.h>
int main (){
	int j,k,adicao=0,i;
	scanf("%d%d",&j,&k);
	if(j>k){
		for(i=k+1;i<j;i++){
			if((i%2!=0)||(i%-2!=0)){
				adicao+=i;
			}


		}

	}else{
		for(i=j+1;i<k;i++){
			if((i%2!=0)||(i%-2!=0)){
				adicao+=i;
			}


		}
	}
	printf("%d\n",adicao);


	return 0;
}
