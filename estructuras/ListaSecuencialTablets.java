package estructuras;

import modelo.Tablet;

public class ListaSecuencialTablets {
    private Tablet[] datos;
    private int tamanio;

    public ListaSecuencialTablets() {
        this(20);
    }

    public ListaSecuencialTablets(int capacidad) {
        if (capacidad <= 0) {
            capacidad = 20;
        }
        datos = new Tablet[capacidad];
        tamanio = 0;
    }

    public boolean insertar(Tablet t) {
        if (t == null) {
            System.out.println("No se puede registrar una tablet nula.");
            return false;
        }

        if (tamanio == datos.length) {
            System.out.println("No se pueden registrar mas tablets. El inventario esta lleno.");
            return false;
        }

        if (buscar(t.getCodigo()) != null) {
            System.out.println("Ya existe una tablet con el codigo " + t.getCodigo() + ".");
            return false;
        }

        if (!estadoValido(t.getEstado())) {
            System.out.println("El estado de la tablet no es valido.");
            return false;
        }

        datos[tamanio] = t;
        tamanio++;
        return true;
    }

    public Tablet buscar(String codigo) {
        if (codigo == null) {
            return null;
        }

        String codigoBuscado = codigo.trim();

        for (int i = 0; i < tamanio; i++) {
            if (datos[i].getCodigo().equalsIgnoreCase(codigoBuscado)) {
                return datos[i];
            }
        }

        return null;
    }

    public void mostrar() {
        if (tamanio == 0) {
            System.out.println("No hay tablets registradas.");
            return;
        }

        for (int i = 0; i < tamanio; i++) {
            System.out.println(datos[i]);
        }
    }

    public boolean modificarEstado(String codigo, String nuevoEstado) {
        Tablet tablet = buscar(codigo);

        if (tablet == null) {
            System.out.println("Tablet no encontrada.");
            return false;
        }

        if (!estadoValido(nuevoEstado)) {
            System.out.println("Estado no valido. Use DISPONIBLE, PRESTADA o MANTENIMIENTO.");
            return false;
        }

        tablet.setEstado(nuevoEstado);
        return true;
    }

    public boolean eliminar(String codigo) {
        if (codigo == null) {
            System.out.println("Tablet no encontrada.");
            return false;
        }

        int posicion = -1;

        for (int i = 0; i < tamanio; i++) {
            if (datos[i].getCodigo().equalsIgnoreCase(codigo.trim())) {
                posicion = i;
                break;
            }
        }

        if (posicion == -1) {
            System.out.println("Tablet no encontrada.");
            return false;
        }

        if (datos[posicion].getEstado().equals("PRESTADA")) {
            System.out.println("No se puede eliminar una tablet que esta PRESTADA.");
            return false;
        }

        for (int i = posicion; i < tamanio - 1; i++) {
            datos[i] = datos[i + 1];
        }

        datos[tamanio - 1] = null;
        tamanio--;
        return true;
    }

    public Tablet buscarDisponible(String tipoUsuario) {
        if (tipoUsuario == null) {
            return null;
        }

        String tipo = tipoUsuario.trim().toUpperCase();

        for (int i = 0; i < tamanio; i++) {
            Tablet tablet = datos[i];

            if (!tablet.getEstado().equals("DISPONIBLE")) {
                continue;
            }

            if (tipo.equals("GRUPO_INVESTIGACION") && tablet.getAlmacenamientoGB() < 32) {
                continue;
            }

            return tablet;
        }

        return null;
    }

    public void cargarDatosPrueba() {
        insertar(new Tablet("TAB001", "Samsung", 64, "Android 13", "DISPONIBLE"));
        insertar(new Tablet("TAB002", "Lenovo", 32, "Android 12", "PRESTADA"));
        insertar(new Tablet("TAB003", "Huawei", 128, "HarmonyOS 3", "DISPONIBLE"));
        insertar(new Tablet("TAB004", "Xiaomi", 16, "Android 11", "DISPONIBLE"));
    }

    public int getTamanio() {
        return tamanio;
    }

    public int getCapacidad() {
        return datos.length;
    }

    private boolean estadoValido(String estado) {
        if (estado == null) {
            return false;
        }

        String estadoNormalizado = estado.trim().toUpperCase();
        return estadoNormalizado.equals("DISPONIBLE")
                || estadoNormalizado.equals("PRESTADA")
                || estadoNormalizado.equals("MANTENIMIENTO");
    }
}
