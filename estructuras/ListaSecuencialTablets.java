package estructuras;

import modelo.Prestamo;
import modelo.Tablet;

/**
 * Lista secuencial (arreglo de capacidad fija) para el inventario de tablets.
 * Responsable: Chalco Tasna Kenneth Mateo
 */
public class ListaSecuencialTablets {

    private Tablet[] datos;
    private int tamanio;

    public ListaSecuencialTablets(int capacidad) {
        datos = new Tablet[capacidad];
        tamanio = 0;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }

    public boolean estaLlena() {
        return tamanio == datos.length;
    }

    public int getTamanio() {
        return tamanio;
    }

    /** Inserta al final. Rechaza si esta llena o si el codigo ya existe. O(n) por la verificacion. */
    public boolean insertar(Tablet tablet) {
        if (estaLlena() || buscar(tablet.getCodigo()) != null) {
            return false;
        }
        datos[tamanio] = tablet;
        tamanio++;
        return true;
    }

    /** Busqueda secuencial por codigo. O(n). */
    public Tablet buscar(String codigo) {
        for (int i = 0; i < tamanio; i++) {
            if (datos[i].getCodigo().equalsIgnoreCase(codigo)) {
                return datos[i];
            }
        }
        return null;
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("No hay tablets registradas.");
            return;
        }
        System.out.println("Codigo  | Marca     | Almacen. | Version      | Estado");
        System.out.println("--------+-----------+----------+--------------+--------------");
        for (int i = 0; i < tamanio; i++) {
            System.out.println(datos[i]);
        }
        System.out.println("Total de tablets: " + tamanio);
    }

    /** Cambia el estado si la tablet existe y el estado es valido. */
    public boolean modificarEstado(String codigo, String nuevoEstado) {
        Tablet tablet = buscar(codigo);
        if (tablet == null || !Tablet.esEstadoValido(nuevoEstado)) {
            return false;
        }
        tablet.setEstado(nuevoEstado);
        return true;
    }

    /**
     * Elimina con validacion: no se puede eliminar una tablet prestada.
     * Desplaza los elementos siguientes una posicion a la izquierda. O(n).
     */
    public boolean eliminar(String codigo) {
        for (int i = 0; i < tamanio; i++) {
            if (datos[i].getCodigo().equalsIgnoreCase(codigo)) {
                if (Tablet.PRESTADA.equals(datos[i].getEstado())) {
                    return false;
                }
                for (int j = i; j < tamanio - 1; j++) {
                    datos[j] = datos[j + 1];
                }
                datos[tamanio - 1] = null;
                tamanio--;
                return true;
            }
        }
        return false;
    }

    /**
     * Devuelve la primera tablet disponible compatible con el tipo de usuario.
     * Regla diferenciadora: a un grupo de investigacion no se le asignan tablets de menos de 32 GB.
     */
    public Tablet buscarDisponible(String tipoUsuario) {
        for (int i = 0; i < tamanio; i++) {
            Tablet t = datos[i];
            if (!Tablet.DISPONIBLE.equals(t.getEstado())) {
                continue;
            }
            if (Prestamo.GRUPO_INVESTIGACION.equals(tipoUsuario) && t.getAlmacenamientoGB() < 32) {
                continue;
            }
            return t;
        }
        return null;
    }

    /** Datos minimos del enunciado + TAB004 para demostrar la regla de 32 GB. */
    public void cargarDatosPrueba() {
        insertar(new Tablet("TAB001", "Samsung", 64, "Android 13", Tablet.DISPONIBLE));
        insertar(new Tablet("TAB002", "Lenovo", 32, "Android 12", Tablet.PRESTADA));
        insertar(new Tablet("TAB003", "Huawei", 128, "HarmonyOS 3", Tablet.DISPONIBLE));
        insertar(new Tablet("TAB004", "Xiaomi", 16, "Android 11", Tablet.DISPONIBLE));
    }
}
