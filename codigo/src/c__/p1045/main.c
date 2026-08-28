#include<stdio.h>
#include<math.h>
int main(){
	double a,b,c,axc;
	scanf("%lf%lf%lf",&a,&b,&c);
	if (a<c){
		axc=c;
		c=a;
		a=axc;
	}
	if (a<b){
		axc=b;
		b=a;
		a=axc;
	}
	if (b<c){
		axc=c;
		c=b;
		b=axc;
	}
	if (a>=(b+c)){
		printf("NAO FORMA TRIANGULO\n");
	}
	else
	{
		if ((pow(a,2)==(pow(b,2)+pow(c,2)))){
			printf("TRIANGULO RETANGULO\n");
		}
		if ((pow(a,2)>(pow(b,2)+pow(c,2)))){
			printf("TRIANGULO OBTUSANGULO\n");
		}
		if ((pow(a,2)<(pow(b,2)+pow(c,2)))){
			printf("TRIANGULO ACUTANGULO\n");
		}
		if((a==b)&&(b==c)){
			printf("TRIANGULO EQUILATERO\n");
		}else{
			if ((b==c)||(b==a)||(a==c)){
				printf("TRIANGULO ISOSCELES\n");
			}
		}
	}
	return 0;
}
