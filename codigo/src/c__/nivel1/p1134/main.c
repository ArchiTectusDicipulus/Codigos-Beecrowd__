#include <stdio.h>
int main (){
	int a,al=0,di=0,ga=0;
	while(a!=4){
		scanf("%d",&a);
		switch(a){
		case 1:
			al+=1;break;
		case 2:
			ga+=1;break;
		case 3:
			di+=1;break;
		}
	}printf("MUITO OBRIGADO\nAlcool: %d\nGasolina: %d\nDiesel: %d\n",al,ga,di);	return 0;
}
