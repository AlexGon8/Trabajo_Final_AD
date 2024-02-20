@ -0,0 +1,37 @@
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char** argv) {

    char c = 0;
    int tuberia[2];

    pipe(tuberia);

    printf("ANTES DE CERRAR %d\n", tuberia[0]);
    //ANTES DE CERRAR
    close(tuberia[0]);
    printf("DESPUES DE CERRAR\n");

    printf("ANTES DE CERRAR %d\n", tuberia[0]);
    //write(tuberia[0], &c, 1);
    //////////////////////////////
    write(tuberia[1], &c, 1);

    printf("Escribe informacion de la tuberia \n");
    exit(0);

}

/*
El comando write se bloquea si no hay nada en su bufer,
es decir al cerrar el read de la tuberia el programa entiende que no abra
nada para escribir puesto que la tuberia est� tapada por el extremo

1 La se�al SIGPIPE se activa por lo cual unix termina el programa al intentar escribir en una tuberia rota

2 no se esta bloqueando, se termina porque es un siteam que tiene unix para cosas malas

3 el programa funciona si intentas escribir en una tuberia aunque l�uego no exista el read, pero si lo ceirras se activa el sigpipe
*/