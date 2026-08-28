#include<stdio.h>
#include<math.h>
int main(){
	double A,B,C,R1,R2;
	scanf("%lf%lf%lf",&A,&B,&C);
	if (((pow(B,2)-4*A*C)>=0)&&(2*A>0)){
		R1=(-B+sqrt(pow(B,2)-4*A*C))/(2.0*A);
		R2=(-B-sqrt(pow(B,2)-4*A*C))/(2.0*A);
		printf("R1 = %.5lf\nR2 = %.5lf\n",R1,R2);
	}else {
		printf("Impossivel calcular\n");
	}
	return 0;
}
