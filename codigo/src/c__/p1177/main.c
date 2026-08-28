#include <stdio.h>

int main() {

	int x[1000], n, i = 0, j = 0, k = 0;  // Don't use extra memory for no reason :)

	scanf("%d", &n);

	while (i < 1000) {
		for (j = 0; j < n; j++) {

			if(i==1000)   //added this so it won't get out of the 1000 range, and avoid Runtime Error.
				break;

			x[i] = j;
			i++;
		}
	} // end of while should be here.
	for (k = 0; k <1000; k++) {
		printf("N[%d] = %d\n", k, x[k]); //print a new line.
	}

}