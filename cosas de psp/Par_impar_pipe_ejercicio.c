@ -0,0 +1,58 @@
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>

#define numeros_maximos 100

int main() {
    int pipefd1[2], pipefd2[2];
    pid_t pid;

    // Crear dos tuber�as
    pipe(pipefd1);
    pipe(pipefd2);


    // Crear un proceso hijo, este hereda las 2 pipes que he creado antes para poder hacer la comunicacion entre ambis 
    pid = fork();

    if (pid == 0) {  // Si es el Proceso hijo deberemos cerrar las partes de las tuberias que no usaremos
        close(pipefd1[1]);  // Cierra el lado de escritura de tuber�a 1 --> porque elpadre sera el primero en escribir
        close(pipefd2[0]);  // Cierra el lado de lectura de tuber�a 2 --> en esta escribira el hijo cuando confime que el padre escribio.

        int numero_Impar_hijo = 1; //declarop el primer numero impar, el hijo escribira impares
        char confirmacion_de_padre; //ESTE CHAR ME AYUDA PARA LEER ALGO Y PROCEDER A ESCRIBIR(DA IGUAL EL MENSAJE)
        for (int i = 0; i < numeros_maximos; i++) {
            // Lee confirmaci�n del padre
            read(pipefd1[0], &confirmacion_de_padre, sizeof(confirmacion_de_padre)); //--> espera confirmacion, el padre debe haber escrito algo, si no hay nada que leer se bloquea.
            printf("Hijo: %d\n", numero_Impar_hijo);
            numero_Impar_hijo += 2;
            // Envia confirmaci�n al padre
            write(pipefd2[1], "h", 1);
        }

        close(pipefd1[0]);
        close(pipefd2[1]);
    }
    else {  // Proceso padre
        close(pipefd1[0]);  // Cierra el lado de lectura de tuber�a 1
        close(pipefd2[1]);  // Cierra el lado de escritura de tuber�a 2

        int numero_Par_padre = 0;
        char confirmacion_de_hijo;//ESTE CHAR ME AYUDA PARA LEER ALGO Y PROCEDER A ESCRIBIR(DA IGUAL EL MENSAJE)
        for (int i = 0; i < numeros_maximos; i++) {
            printf("Padre: %d\n", numero_Par_padre);
            numero_Par_padre += 2;
            // Envia confirmaci�n al hijo
            write(pipefd1[1], "p", 1); // EL PADRE SERA EL PRIMERO EN ESCRIBIR ALGO // si ya hay algo espera a vaciarse el bufer
            // Lee confirmaci�n del hijo
            read(pipefd2[0], &confirmacion_de_hijo, sizeof(confirmacion_de_hijo)); //--> espera la confirmacion del hijo, ose que ecriba algo en la segunda pipe
        }

        close(pipefd1[1]);
        close(pipefd2[0]);
    }

    return 0;
}