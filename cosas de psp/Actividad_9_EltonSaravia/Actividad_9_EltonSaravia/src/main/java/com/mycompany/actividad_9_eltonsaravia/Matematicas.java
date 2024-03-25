/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.actividad_9_eltonsaravia;



public class Matematicas {
   
    private String nombre;
    private int edad;
    private double numeroFavorito;
    private String empleo;

    public Matematicas(String nombre, int edad, double numeroFavorito, String empleo) {
        this.nombre = nombre;
        this.edad = edad;
        this.numeroFavorito = numeroFavorito;
        this.empleo = empleo;
    }

    public Matematicas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getNumeroFavorito() {
        return numeroFavorito;
    }

    public String getEmpleo() {
        return empleo;
    }
   
   
    public static int absoluto (int a){
        if (a < 0){
            a = -a;
        }
        return a;
    }
   
    public static double absoluto(double a){
        if (a<0){
            a = -a;
        }
        return a;
    }
   
    public static int maximo (int a, int b){
        if (a > b){
            return a;
        }
        return b;
    }
   
    public static int minimo (int a, int b){
        if (a > b){
            return b;
        }
        return a;
    }
   
    public static int redondear (double a){
        if (a - (int)a >= 0.5){
            return (int)a + 1;
        }
        return (int)a;
    }
   
    public static int redondearAlza (double a){
        //6.1 -> 7
        //6.0 -> 6.0
        if (a != (int)a){
            return (int)a + 1;
        }
        return (int)a;
    }
   
    public static int redondearBaja (double a){
        return (int)a;
    }
   
    public static int potencia (int base, int exponente){
        int resultado =1;
       
        for (int i = 0; i < exponente; i++){
            resultado = resultado * base;
        }
        return resultado;
    }
   
    public static int aleatorio(int fin){
        return (int)(Math.random()*(fin + 1));
    }
   
    public static int aleatorio (int inicio, int fin){
        return (int)(Math.random()*(fin-inicio)) + inicio;
    }
   
    public static int suma(int num1, int num2){
      return num1 + num2;
    }
   
    public static int resta(int num1, int num2){
      return num1 - num2;
    }
   
    public static int multiplicacion(int num1, int num2){
      return num1 * num2;
    }
   
    public static float division(float num1, float num2){
      return num1 / num2;
    }
   
    public static float calcularResto(float dividendo, float divisor) {
        // Calcular el resto usando el operador %
        return dividendo % divisor;
    }
   
    public static int[] generarArray(int valor){
        int longitudArray = valor + 1;
        int[] arrayResultante = new int[longitudArray];

        for (int i = 0; i < longitudArray; i++) {
            arrayResultante[i] = valor - i;
        }
        return arrayResultante;        
    }
   
    public static String stringNulo(){
        return null;
    }
   
    public static Object objetoNulo(){
        return null;
    }
   
    public Matematicas crearMatematico(String Nombre, int Edad, double numeroFavorito, String empleo){
        return new Matematicas(Nombre, Edad, numeroFavorito, empleo);
    }
   
    public static double calcularAreaCirculo(double radio) {
        
        return 3.14*radio*radio;
    }
   
}