package servicio;

import java.util.Scanner;
import modelo.Prestamo;

/**
 * Validaciones de entrada por teclado.
 * Responsable: Cunalata Mendoza Damian Alexander
 */
public class Validador {

    public static boolean esCedulaValida(String cedula) {
        if (cedula == null || cedula.length() != 10) {
            return false;
        }
        for (int i = 0; i < cedula.length(); i++) {
            if (!Character.isDigit(cedula.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String leerTexto(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("  ERROR: el campo no puede estar vacio.");
        }
    }

    public static int leerEntero(Scanner sc, String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            try {
                int valor = Integer.parseInt(texto);
                if (valor >= min && valor <= max) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                // se muestra el mismo mensaje de error
            }
            System.out.println("  ERROR: ingrese un numero entre " + min + " y " + max + ".");
        }
    }

    public static String leerCedula(Scanner sc) {
        while (true) {
            String cedula = leerTexto(sc, "Cedula (10 digitos): ");
            if (esCedulaValida(cedula)) {
                return cedula;
            }
            System.out.println("  ERROR: la cedula debe tener exactamente 10 digitos numericos.");
        }
    }

    public static String leerCodigo(Scanner sc) {
        return leerTexto(sc, "Codigo de la tablet: ").toUpperCase();
    }

    public static String leerTipoUsuario(Scanner sc) {
        System.out.println("Tipo de usuario: 1. Estudiante  2. Grupo de investigacion");
        int tipo = leerEntero(sc, "Seleccione: ", 1, 2);
        return tipo == 1 ? Prestamo.ESTUDIANTE : Prestamo.GRUPO_INVESTIGACION;
    }
}
