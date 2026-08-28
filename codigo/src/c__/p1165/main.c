#include<stdio.h>
int main(){
	int a,f,s,n,y;
	scanf("%d",&f);
	for(y=0;y<f;y++){
		s=1;
		scanf("%d",&a);
		for(n=(a-1);n!=1;n--){
			if(a%n==0){
				s=0;
			}
		}
		if(s!=0){
			printf("%d eh primo\n",a);
		}else{
			printf("%d nao eh primo\n",a);
		}
	}
	return 0;
}
