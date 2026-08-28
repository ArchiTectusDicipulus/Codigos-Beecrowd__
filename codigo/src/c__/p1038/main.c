#include<stdio.h>
int main(){
	int codigo=0,codigo2=0;
	float Total=0,Total2=0,t=0;
	scanf("%d%d",&codigo,&codigo2);
	switch(codigo){
	case 1:
		Total=4*codigo2;break;
	case 2:
		Total=4.5*codigo2;break;
	case 3:
		Total=5*codigo2;break;
	case 4:
		Total=2*codigo2;break;
	case 5:
		Total=1.5*codigo2;break;
	}
	printf("Total: R$ %.2f\n",Total);
	return 0;
}
