#include<stdio.h>
int main(){
	int menor=9999,p,n,b,l[9999];
	scanf("%d",&n);
	b=0;
	while(b<n){
		scanf("%d",&l[b]);
		if(l[b]<menor){
			menor=l[b];
			p=b;
		}b++;
	}
	printf("Menor valor: %d\nPosicao: %d\n",menor,p);
	return 0;
}
