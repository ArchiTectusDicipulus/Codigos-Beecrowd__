#include <stdio.h>
int main (){
	int u;
	float media,I;
	for (u=0;u<3;u++){
		scanf ("%f",&I);
		if ((I>10)||(I<0)){
			u--;
			printf ("nota invalida\n");
		}else{
			media=I+media;
			u++;
		}
	}
	printf ("media = %.2f\n",media/2);
	return 0;
}
