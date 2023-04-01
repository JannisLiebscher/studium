/*
 * nichtsalsfehler.c
 *
 * Beispielprogramm fuer die Fehlersuche mit valgrind.
 *
 * Autor: H.Drachenfels
 * Erstellt am: 23.7.2018
 */

#include <stdlib.h>

int main(void)
{
    int *p1 = (int*) malloc(sizeof (int));
    int *p2 = (int*) malloc(2 * sizeof (int));
    int *p3 = (int*) calloc(1, sizeof (int));

    for (int i = 1; i < 3; ++i)
    {
        p2[i] = 0;          // p2[2] out of Index
    }

    *p1 = p2[0];            // p2[0] = NULL
    *p3 = *p1 ? p2[0] : p2[1];  // *p3 = P2[1] geht nicht weil p3 nur Index 0 hat

    free(p2);
    free(p3);
    *p3 = 0;                // Zuweisung nach free()
    free(p3);               // Zweites mal free() auf p3
                            // free(p1) fehlt
    return 0;
}

