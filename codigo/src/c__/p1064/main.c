#include<stdio.h>
int main(){
	int qtp=0;
	float a=0,b=0,c=0,d=0,e=0,f=0,media=0;
	scanf("%f%f%f%f%f%f",&a,&b,&c,&d,&e,&f);
	if(a>0){
		media+=a;
		qtp+=1;
	}
	if(b>0){
		media+=b;
		qtp+=1;
	}

	if(c>0){
		media+=c;
		qtp+=1;
	}
	if(d>0){
		media+=d;
		qtp+=1;
	}
	if(e>0){
		media+=e;
		qtp+=1;
	}
	if(f>0){
		media+=f;
		qtp+=1;
	}
	printf("%d valores positivos\n",qtp);
	if(qtp!=0){
		printf("%.1f\n",media/qtp);
	}else{
		printf("0");
	}
	return 0;
}
