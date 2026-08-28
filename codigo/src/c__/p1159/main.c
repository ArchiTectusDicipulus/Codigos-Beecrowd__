#include<stdio.h>
int main(){
	int a;
	scanf("%d",&a);
	while(a!=0){
		if(a%2==0){
			a=a*5+20;
		}else{
			a++;
			a=a*5+20;
		}
		printf("%d\n",a);
		scanf("%d",&a);
	}
}
