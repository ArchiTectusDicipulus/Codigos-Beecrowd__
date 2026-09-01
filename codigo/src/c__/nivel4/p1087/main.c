#include<stdio.h>
#include <stdlib.h>
int main(){
	int a,b,c,d,x;
	int y;
	do{
		scanf("%d%d%d%d",&a,&b,&c,&d);
		if(a==0&&b==0&&c==0&&d==0){break;}
		if(a==c&&b==d){
			printf("0\n");
		}else{
			if(a==c||b==d){
				printf("1\n");
			}else{
				if(abs(a-c)==abs(b-d)){
					printf("1\n");

				}else{
					printf("2\n");
				}
			}

		}
	}while(1);
	return 0;
}
