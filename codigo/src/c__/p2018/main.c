#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct tipoDados{

	char nome[50];
	unsigned short ouro;
	unsigned short prata;
	unsigned short bronze;

} tipoDados;

struct noArv{

	tipoDados dado;
	struct noArv *esquerdo;
	struct noArv *direito;

};

typedef struct noArv Arv;

Arv* inserirOuro(Arv *a, tipoDados dado);
Arv* inserirBronze(Arv *a, tipoDados dado);
Arv* inserirPrata(Arv *a, tipoDados dado);
void arvoreParaVetor(Arv *a, tipoDados *vetor);
int compara(const void *a, const void *b);
unsigned short i = 0;

int main (){

	unsigned short j;
	char modalidade[300];
	Arv *a = NULL;

	while (scanf(" %[^\n]", modalidade) != EOF){

		tipoDados pais = { 0 };
		scanf(" %[^\n]", pais.nome);
		a = inserirOuro(a, pais);
		scanf(" %[^\n]", pais.nome);
		a = inserirPrata(a, pais);
		scanf(" %[^\n]", pais.nome);
		a = inserirBronze(a, pais);

	}

	tipoDados competidores[300] = { 0 };
	printf("Quadro de Medalhas\n");
	arvoreParaVetor(a, competidores);
	qsort(competidores, i, sizeof(tipoDados), compara);

	for (j = 0; j < i; j++)
		printf("%s %hu %hu %hu\n", competidores[j].nome, competidores[j].ouro, competidores[j].prata, competidores[j].bronze);

}

void arvoreParaVetor(Arv *a, tipoDados *vetor){

	if (a != NULL){

		arvoreParaVetor(a->esquerdo, vetor);
		vetor[i++] = a->dado;
		arvoreParaVetor(a->direito, vetor);

	}

}

Arv* inserirOuro(Arv *a, tipoDados dado){

	if (!a){

		a = (Arv *) malloc(sizeof(Arv));
		a->dado = dado;
		a->dado.ouro++;
		a->esquerdo = a->direito = NULL;

	}
	else if (strcmp(a->dado.nome, dado.nome) > 0)
		a->esquerdo = inserirOuro(a->esquerdo, dado);
	else if (strcmp(a->dado.nome, dado.nome) < 0)
		a->direito = inserirOuro(a->direito, dado);
	else
		a->dado.ouro++;

	return a;

}

Arv* inserirPrata(Arv *a, tipoDados dado){

	if (!a){

		a = (Arv *) malloc(sizeof(Arv));
		a->dado = dado;
		a->dado.prata++;
		a->esquerdo = a->direito = NULL;

	}
	else if (strcmp(a->dado.nome, dado.nome) > 0)
		a->esquerdo = inserirPrata(a->esquerdo, dado);
	else if (strcmp(a->dado.nome, dado.nome) < 0)
		a->direito = inserirPrata(a->direito, dado);
	else
		a->dado.prata++;

	return a;

}

Arv* inserirBronze(Arv *a, tipoDados dado){

	if (!a){

		a = (Arv *) malloc(sizeof(Arv));
		a->dado = dado;
		a->dado.bronze++;
		a->esquerdo = a->direito = NULL;

	}
	else if (strcmp(a->dado.nome, dado.nome) > 0)
		a->esquerdo = inserirBronze(a->esquerdo, dado);
	else if (strcmp(a->dado.nome, dado.nome) < 0)
		a->direito = inserirBronze(a->direito, dado);
	else
		a->dado.bronze++;

	return a;

}

int compara(const void *a, const void *b){

	if ((*(struct tipoDados*)a).ouro == (*(struct tipoDados*)b).ouro){
		if ((*(struct tipoDados*)a).prata == (*(struct tipoDados*)b).prata){
			if ((*(struct tipoDados*)a).bronze == (*(struct tipoDados*)b).bronze){
				if (strcmp((*(struct tipoDados*)a).nome, (*(struct tipoDados*)b).nome) == 0)
					return 0;
				else if ((strcmp((*(struct tipoDados*)a).nome, (*(struct tipoDados*)b).nome) > 0))
					return 1;
				else
					return -1;
			}
			else if ((*(struct tipoDados*)a).bronze > (*(struct tipoDados*)b).bronze)
				return -1;
			else
				return 1;
		}
		else if ((*(struct tipoDados*)a).prata > (*(struct tipoDados*)b).prata)
			return -1;
		else
			return 1;
	}
	else if ((*(struct tipoDados*)a).ouro > (*(struct tipoDados*)b).ouro)
		return -1;
	else
		return 1;
}