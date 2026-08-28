#include<stdio.h>
int main(){
	int a,f,s=0,n,y;
	scanf("%d",&f);
	for(y=0;y<f;y++){
		scanf("%d",&a);
		for(n=(a-1);n!=0;n--){
			if(a%n==0){
				s+=n;
			}
		}
		if(s==a){
			printf("%d eh perfeito\n",a);
		}else{
			printf("%d nao eh perfeito\n",a);
		}s=0;
	}
	return 0;
}
