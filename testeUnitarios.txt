/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.actividad_9_eltonsaravia.Matematicas;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alumno
 */
public class ProbarMatematicas {
    
    public ProbarMatematicas() {
    }

   
    @Test
    void testAbsolutoIntPositivo() {
        assertEquals(1, Matematicas.absoluto(1));
    }

    @Test
    void testAbsolutoIntNegativo() {
        assertEquals(5, Matematicas.absoluto(-5));
    }

    @Test
    void testAbsolutoDoublePositivo() {
        assertEquals(5.0, Matematicas.absoluto(5.0));
    }

    @Test
    void testAbsolutoDoubleNegativo() {
        assertEquals(5.0, Matematicas.absoluto(-5.0));
    }

    @Test
    void testMaximoMayorPrimero() {
        assertEquals(10, Matematicas.maximo(10, 5));
    }

    @Test
    void testMaximoMayorSegundo() {
        assertEquals(10, Matematicas.maximo(5, 10));
    }

   
     @Test
    public void testMinimoMayorPrimero() {
    assertEquals(5, Matematicas.minimo(10, 5));
    }
    
     @Test
    public void testMinimoMenorSegundo() {
    assertEquals(5, Matematicas.minimo(10, 5));
    }
    
     @Test
    public void testRedondear() {
    assertEquals(11, Matematicas.redondear(10.5));
    }
    
    public void testredondearAlza() {
    assertEquals(7, Matematicas.redondearAlza(6.1));
    }
    public void testredondearBaja() {
    assertEquals(6, Matematicas.redondearBaja(6.1));
    }
    public void testPotencia() {
    assertEquals(4, Matematicas.potencia(2,2));
    }
    
    
    @Test
    void testAleatorioGeneraDiferentesValores() {
        int fin = 10;
        int primerResultado = Matematicas.aleatorio(fin);
        boolean hayDiferencia = false;
        for (int i = 0; i < 1000; i++) {
            if (Matematicas.aleatorio(fin) != primerResultado) {
                hayDiferencia = true;
                break;
            }
        }
        assertTrue(hayDiferencia);
    }
    
     @Test
    void testAleatorioRangoEspecificoGeneraDiferentesValores() {
        int inicio = 5;
        int fin = 15;
        int primerResultado = Matematicas.aleatorio(inicio, fin);
        boolean hayDiferencia = false;
        for (int i = 0; i < 1000; i++) {
            if (Matematicas.aleatorio(inicio, fin) != primerResultado) {
                hayDiferencia = true;
                break;
            }
        }
        assertTrue(hayDiferencia);
    }
    public void testSuma() {
    assertEquals(4, Matematicas.suma(2,2));
    }
    
        public void testResta() {
    assertEquals(2, Matematicas.resta(4,2));
    }
    
     public void testMultiplicacion() {
    assertEquals(8, Matematicas.multiplicacion(4,2));
    }
    
    public void testDivision() {
    assertEquals(4, Matematicas.division(8,2));
    }
    
    public void testcalcularResto() {
    assertEquals(0, Matematicas.calcularResto(4,2));
    }
    
    public void testcalcularRestoMal() {
    assertEquals(8, Matematicas.calcularResto(4,2));
    }
    
    
    ///////////////////
    
   
    /*LOS DE ARRAYS*/
       @Test
    void testGenerarArrayLongitudCorrecta() {
        int valor = 5;
        int[] resultado = Matematicas.generarArray(valor);
        assertEquals(valor + 1, resultado.length);
    }

    @Test
    void testGenerarArrayContenidoCorrecto() {
        int valor = 5;
        int[] resultado = Matematicas.generarArray(valor);
        for (int i = 0; i <= valor; i++) {
            assertEquals(valor - i, resultado[i]);
        }
    }
    
    
    @Test
    void testStringNuloRetornaNull() {
    assertNull(Matematicas.stringNulo());
}

    @Test
    void testStringNuloNoRetornaNonNull() {
        assertNotEquals("", Matematicas.stringNulo());
    }
    
    @Test
void testObjetoNuloRetornaNull() {
    assertNull(Matematicas.objetoNulo());
}

@Test
void testObjetoNuloNoRetornaNonNull() {
    assertNotEquals(new Object(), Matematicas.objetoNulo());
}

@Test
void testCalcularAreaCirculoRadioCero() {
    assertEquals(0.0, Matematicas.calcularAreaCirculo(0));
}

@Test
void testCalcularAreaCirculoRadioPositivo() {
    double radio = 2.0;
    double areaEsperada = 3.14 * radio * radio;
    assertEquals(areaEsperada, Matematicas.calcularAreaCirculo(radio));
}

@Test
void testCrearMatematicoNoEsNull() {
    Matematicas matematico = new Matematicas().crearMatematico("isla", 30, 3.14, "Profesor");
    assertNotNull(matematico);
}

@Test
void testCrearMatematicoAtributosCorrectos() {
    Matematicas matematico = new Matematicas().crearMatematico("JAVIER", 30, 3.14, "Profesor");
    assertEquals("Juan", matematico.getNombre());
    assertEquals(30, matematico.getEdad());
    assertEquals(3.14, matematico.getNumeroFavorito());
    assertEquals("Profesor", matematico.getEmpleo());
}
    
}
