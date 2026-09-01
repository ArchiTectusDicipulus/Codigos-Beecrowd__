#include<stdio.h>
int main(){
	float n1,n2,n3,n4,NtF;
	scanf("%f%f%f%f",&n1,&n2,&n3,&n4);
	n1=(n1*2+n2*3+n3*4+n4)/10;
	printf("Media: %.1f\n",n1);
	if(n1>=7){
		printf("Aluno aprovado.\n");
	}else{
		if(n1>=5&&n1<=6.9){
			scanf("%f",&NtF);
			printf("Aluno em exame.\n");
			printf("Nota do exame: %.1f\n",NtF);
			NtF=(n1+NtF)/2;
			if(NtF>=5){
				printf("Aluno aprovado.\n");
			}else{
				printf("Aluno reprovado.\n");
			}
			printf("Media final: %.1f\n",NtF);
		}else{
			printf("Aluno reprovado.\n");
		}
	}
	return 0;
}
