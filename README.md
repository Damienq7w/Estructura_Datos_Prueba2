# Estructura_Datos_Prueba2

# Sistema de Tablets para Biblioteca Digital — Estructura de Datos (Java)

# Grupo - 04

Prueba práctica integradora por grupos de Estructura de Datos (UTA). Aplicación de consola en Java,
desarrollada en Visual Studio Code con JDK 17, que administra el préstamo de tablets de la
biblioteca tecnológica usando **seis estructuras de datos implementadas con nodos propios**, sin
`LinkedList`, `Stack`, `Queue`, `ArrayDeque` ni otras colecciones de Java.

**Repositorio:** https://github.com/Damienq7w/Estructura_Datos_Prueba2

## Integrantes

| Integrante | Usuario GitHub |
| --- | --- |
| Cunalata Mendoza Damian Alexander | [`Damienq7w`](https://github.com/Damienq7w) |
| Chalco Tasna Kenneth Mateo | [`KEN3DYY`](https://github.com/KEN3DYY) |
| Silva Camuendo Luis Alexander | [`luuissilva`](https://github.com/luuissilva) |
| Tisalema Guashco Darwin Joel | [`Joel03032007`](https://github.com/Joel03032007) |
| Tacuri Santillan Mónica Sara | [`tacurisantillanmonicasara-dot`](https://github.com/tacurisantillanmonicasara-dot) |
| Camacho Monta Josue Jampier | [`jampiercamacho94-cloud`](https://github.com/jampiercamacho94-cloud) |

## Caso asignado

**Grupo 4 — Sistema de tablets para biblioteca digital.**

La biblioteca tecnológica presta tablets a estudiantes para consultar libros digitales y acceder a
aulas virtuales. El sistema debe:

- Registrar tablets por código, marca, almacenamiento, versión del sistema y estado.
- Administrar los préstamos activos asociados al número de cédula.
- Enviar las solicitudes a una cola cuando todas las tablets estén ocupadas.
- Rotar turnos de lectura en una tablet de consulta rápida.
- Deshacer la última devolución si fue registrada con un usuario equivocado.

**Regla diferenciadora:** las tablets con almacenamiento **menor a 32 GB** no pueden asignarse a
**grupos de investigación**. Por eso cada préstamo registra el tipo de usuario: `ESTUDIANTE` o
`GRUPO_INVESTIGACION`.

**Actores:** estudiantes y grupos de investigación (solicitan tablets) y el encargado de la
biblioteca (registra préstamos, devoluciones, mantenimiento y turnos).

## Distribución del trabajo

| Integrante | Responsabilidad | Estructura / módulo | Archivos | Rama | Estado de avance |
| --- | --- | --- | --- | --- | --- |
| Cunalata Mendoza Damian Alexander | Líder técnico e integración | Menú, integración de estructuras, validaciones y regla de 32 GB | `README.md`, `Main.java`, `servicio/BibliotecaService.java`, `servicio/Validador.java` | `Damian_Cunalata` | Completado |
| Chalco Tasna Kenneth Mateo | Desarrollo | Lista secuencial (inventario) | `modelo/Tablet.java`, `estructuras/ListaSecuencialTablets.java` | `Kenneth_Chalco` | Completado |
| Silva Camuendo Luis Alexander | Desarrollo | Lista simplemente enlazada (préstamos activos) | `modelo/Prestamo.java`, `estructuras/NodoPrestamo.java`, `estructuras/ListaSimplePrestamos.java` | `Luis-Silva` | Completado |
| Tisalema Guashco Darwin Joel | Desarrollo | Cola (solicitudes) y pila (deshacer) | `modelo/Solicitud.java`, `estructuras/NodoSolicitud.java`, `estructuras/ColaSolicitudes.java`, `estructuras/NodoAccion.java`, `estructuras/PilaDeshacer.java` | `Rama-Joel` | Completado |
| Tacuri Santillan Mónica Sara | Desarrollo | Lista doblemente enlazada (historial) y lista circular (turnos) | `modelo/Movimiento.java`, `estructuras/NodoHistorial.java`, `estructuras/ListaDobleHistorial.java`, `estructuras/NodoTurno.java`, `estructuras/ListaCircularTurnos.java` | `Sara-Tacuri` | Completado |
| Camacho Monta Josue Jampier | Documentación | Documento, capturas y evidencia de pruebas | `Informe/Prueba_02_Estructura_Datos.pdf`, `Capturas_Ejecucion/` | `rama-Josue` | Completado |

## Estructura del proyecto

```
Estructura_Datos_Prueba2/
├── .vscode/                             → configuración para ejecutar Main con Run (F5)
│   ├── launch.json
│   ├── settings.json
│   └── tasks.json
├── ExamenED_Grupo4/                     → código fuente del proyecto
│   ├── Main.java                        → menú principal (punto de entrada)
│   ├── modelo/
│   │   ├── Tablet.java                  → código, marca, almacenamiento, versión del sistema y estado
│   │   ├── Prestamo.java                → cédula, nombre, tipo de usuario y código de tablet
│   │   ├── Solicitud.java               → solicitud en espera
│   │   └── Movimiento.java              → registro del historial (tipo, descripción, fecha y hora)
│   ├── estructuras/
│   │   ├── ListaSecuencialTablets.java  → inventario (arreglo)
│   │   ├── NodoPrestamo.java
│   │   ├── ListaSimplePrestamos.java    → préstamos activos
│   │   ├── NodoSolicitud.java
│   │   ├── ColaSolicitudes.java         → solicitudes en espera
│   │   ├── NodoAccion.java
│   │   ├── PilaDeshacer.java            → deshacer la última devolución
│   │   ├── NodoHistorial.java
│   │   ├── ListaDobleHistorial.java     → historial de movimientos
│   │   ├── NodoTurno.java
│   │   └── ListaCircularTurnos.java     → turnos de lectura
│   └── servicio/
│       ├── BibliotecaService.java       → integra las estructuras y aplica las reglas del negocio
│       └── Validador.java               → validaciones de entrada
├── Informe/
│   └── Prueba_02_Estructura_Datos.pdf   → informe: caso, estructuras, casos de prueba y capturas
├── Capturas_Ejecucion/                  → capturas de la ejecución en Visual Studio Code
├── .gitignore
└── README.md
```

- `modelo/` guarda los datos del caso real.
- `estructuras/` contiene las seis estructuras con sus nodos.
- `servicio/` une todo y aplica las reglas.
- `Main` solo muestra el menú y llama al servicio.

## Compilar y ejecutar

**Requisitos:** JDK 17 o superior y Visual Studio Code con el *Extension Pack for Java*.

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Damienq7w/Estructura_Datos_Prueba2.git
   ```

**Opción A — botón Run (recomendada):** en Visual Studio Code usar **File → Open Folder** y abrir la
carpeta raíz `Estructura_Datos_Prueba2`. La carpeta `.vscode/` ya está configurada: al pulsar
**Run → Start Debugging (F5)** y elegir *Ejecutar Main*, se compila el proyecto en `bin/` y se
ejecuta `Main`.

**Opción B — terminal integrada** (`Ctrl + ñ`), desde la carpeta `ExamenED_Grupo4`:
```bash
cd ExamenED_Grupo4
javac -d bin Main.java modelo/*.java estructuras/*.java servicio/*.java
java -cp bin Main
```

> `javac *.java` no es suficiente porque solo compila `Main.java`. Las clases de `modelo/`,
> `estructuras/` y `servicio/` están en paquetes y deben incluirse en el comando.

Al iniciar, el programa carga automáticamente los datos de prueba y muestra el menú.

## Datos de prueba

Los tres primeros son los datos mínimos del enunciado. `TAB004` se agregó para demostrar la regla
diferenciadora, porque ninguna de las otras tiene menos de 32 GB.

| Código | Marca | Almacenamiento | Versión del sistema | Estado inicial |
| --- | --- | --- | --- | --- |
| TAB001 | Samsung | 64 GB | Android 13 | Disponible |
| TAB002 | Lenovo | 32 GB | Android 12 | Prestada a María López (cédula 1804567890) |
| TAB003 | Huawei | 128 GB | HarmonyOS 3 | Disponible |
| TAB004 | Xiaomi | 16 GB | Android 11 | Disponible |

## Menú principal

```
 1. Inventario de tablets
 2. Prestar tablet
 3. Devolver tablet
 4. Prestamos activos
 5. Cola de solicitudes
 6. Historial
 7. Turnos de lectura (tablet de consulta rapida)
 8. Mantenimiento
 9. Deshacer ultima devolucion
 0. Salir
```

### Cómo se realiza cada operación

| Opción | Qué hace | Cómo funciona por dentro |
| --- | --- | --- |
| **1. Inventario** | Submenú: registrar, buscar, mostrar, modificar estado y eliminar tablets | Trabaja sobre la **lista secuencial**. Al registrar se valida que el código no exista. El estado solo puede cambiarse manualmente a `DISPONIBLE` o `MANTENIMIENTO`; una tablet pasa a `PRESTADA` únicamente mediante un préstamo. No se puede eliminar ni cambiar de estado una tablet prestada. |
| **2. Prestar** | Pide cédula, nombre y tipo de usuario | Verifica que la cédula no tenga préstamo activo ni solicitud en espera. Busca en el inventario la primera tablet `DISPONIBLE` compatible, aplicando la regla de 32 GB. Si la encuentra, la marca `PRESTADA` y agrega el préstamo a la **lista simple**. Si no, crea una solicitud y la envía a la **cola**. Todo se registra en el **historial**. |
| **3. Devolver** | Pide la cédula | Elimina el préstamo de la **lista simple**, deja la tablet `DISPONIBLE` y **apila** el préstamo devuelto en la **pila** para poder deshacerlo. Si hay solicitudes en espera, lo avisa. |
| **4. Préstamos activos** | Lista los préstamos | Recorre la **lista simple** desde la cabeza. |
| **5. Cola** | Submenú: consultar frente, listar y atender siguiente | *Atender* revisa la solicitud del frente. Si hay una tablet compatible, la **desencola** y crea el préstamo; si no, la solicitud sigue esperando en el frente. |
| **6. Historial** | Recorre hacia adelante o hacia atrás | Recorre la **lista doble** de `cabeza` a `cola` (más antiguo → más reciente) o de `cola` a `cabeza` (más reciente → más antiguo). |
| **7. Turnos** | Submenú: agregar lector, avanzar, eliminar actual y mostrar ronda | Trabaja sobre la **lista circular**. Después del último lector vuelve el primero. |
| **8. Mantenimiento** | Enviar o retornar una tablet | Una tablet prestada no puede ir a mantenimiento. Una tablet en mantenimiento no se asigna a nadie. |
| **9. Deshacer** | Revierte la última devolución | Consulta el **tope de la pila**. Si la tablet sigue `DISPONIBLE`, **desapila** el préstamo, lo vuelve a insertar en la lista simple y marca la tablet `PRESTADA`. Si la tablet ya fue prestada a otra persona, no permite deshacer. |

### Validaciones

- Cédula de exactamente 10 dígitos numéricos.
- Campos obligatorios no vacíos.
- Opciones del menú numéricas y dentro del rango; si se ingresan letras, el programa no se cierra.
- Códigos de tablet no repetidos al registrar y existentes al buscar, modificar o eliminar.
- Una cédula no puede tener dos préstamos activos ni dos solicitudes en espera.
- No se puede eliminar, cambiar de estado ni enviar a mantenimiento una tablet prestada.
- No se asignan tablets de menos de 32 GB a grupos de investigación.
- Mensajes claros cuando no hay préstamos, solicitudes, devoluciones para deshacer, historial o
  lectores en la ronda.

## Explicación de cada estructura

| Estructura | Clase | Uso en el sistema | Operaciones | Complejidad principal |
| --- | --- | --- | --- | --- |
| Lista secuencial | `ListaSecuencialTablets` | Inventario de tablets | Insertar, buscar, mostrar, modificar estado, eliminar con validación | Buscar O(n), eliminar O(n) |
| Lista simplemente enlazada | `ListaSimplePrestamos` | Préstamos activos por cédula | Insertar, buscar, eliminar, recorrer | Buscar/eliminar O(n) |
| Cola | `ColaSolicitudes` | Solicitudes en espera | Encolar, desencolar, consultar frente, listar | Encolar/desencolar O(1) |
| Lista doblemente enlazada | `ListaDobleHistorial` | Historial de movimientos | Insertar al final, recorrer adelante y atrás | Insertar O(1) |
| Pila | `PilaDeshacer` | Deshacer la última devolución | Apilar, desapilar, consultar tope, restaurar | Apilar/desapilar O(1) |
| Lista circular | `ListaCircularTurnos` | Turnos de lectura | Insertar, avanzar, eliminar actual, mostrar ronda | Avanzar O(1) |

### 1. Lista secuencial — Inventario (`ListaSecuencialTablets`)

**Cómo funciona:** guarda las tablets en un arreglo `Tablet[]` de capacidad fija con un contador
`tamanio`.
- `insertar` coloca la tablet en la posición `tamanio`, después de verificar que no esté llena y
  que el código no se repita.
- `buscar` recorre el arreglo comparando códigos.
- `eliminar` no permite borrar una tablet prestada. Si se puede, corre una posición a la izquierda
  todos los elementos siguientes.
- `buscarDisponible(tipoUsuario)` aplica la **regla diferenciadora**: si el usuario es un grupo de
  investigación, se salta las tablets de menos de 32 GB.

**Por qué se eligió:** el inventario es pequeño y casi fijo, porque la biblioteca tiene un número
conocido de tablets. Un arreglo reserva el espacio una sola vez y permite acceso directo por
posición en O(1). El costo de correr elementos al eliminar es mínimo con un inventario de este
tamaño.

### 2. Lista simplemente enlazada — Préstamos activos (`ListaSimplePrestamos`)

**Cómo funciona:** cada préstamo vive en un `NodoPrestamo` con referencia `siguiente`, y la lista
parte de `cabeza`.
- `insertar` recorre hasta el último nodo y engancha el nuevo.
- `buscar` localiza el préstamo por cédula.
- `eliminar` cubre tres casos: lista vacía, el nodo es la cabeza (`cabeza = cabeza.siguiente`), o
  el nodo está en medio o al final (el nodo anterior pasa a apuntar al siguiente del eliminado).
  Devuelve el préstamo eliminado para enviarlo a la pila.

**Por qué se eligió:** los préstamos se crean y se eliminan constantemente y no se sabe cuántos
habrá. En una lista enlazada, eliminar un préstamo solo cambia un enlace: no hay que desplazar
elementos ni reservar espacio de antemano.

### 3. Cola — Solicitudes en espera (`ColaSolicitudes`)

**Cómo funciona:** mantiene referencias a `frente` y `fin`.
- `encolar` agrega por `fin`.
- `desencolar` saca por `frente`; si la cola queda vacía, `fin` vuelve a `null`.
- `verFrente` consulta sin sacar.
- `listar` muestra la espera en orden de llegada.

**Por qué se eligió:** cuando no hay tablets, lo justo es atender por orden de llegada: el primero
que pidió es el primero en recibir. Ese comportamiento es **FIFO**. Gracias a `frente` y `fin`,
encolar y desencolar son O(1).

### 4. Lista doblemente enlazada — Historial (`ListaDobleHistorial`)

**Cómo funciona:** cada `NodoHistorial` tiene `anterior` y `siguiente`, y la lista mantiene
`cabeza` y `cola`.
- `insertarFinal` enlaza el nuevo nodo con la cola actual en los dos sentidos.
- `recorrerAdelante` va de `cabeza` a `cola`.
- `recorrerAtras` va de `cola` a `cabeza`.

**Por qué se eligió:** el historial se consulta en los dos sentidos: completo desde el inicio, o
desde lo más reciente. El doble enlace permite recorrer hacia atrás sin invertir la lista ni usar
otra estructura, y la referencia `cola` hace que registrar cada operación sea O(1).

### 5. Pila — Deshacer última devolución (`PilaDeshacer`)

**Cómo funciona:** mantiene la referencia `tope`.
- Cada devolución se **apila** con los datos del préstamo original (cédula, nombre, tipo y tablet).
- Al deshacer se **consulta el tope** y se valida que la tablet siga disponible.
- Si se puede, se **desapila** y se **restaura** el préstamo en la lista simple.

**Por qué se eligió:** el error que se corrige es la **última** devolución registrada con un
usuario equivocado. Lo último que entra es lo primero que se deshace: comportamiento **LIFO**.
Apilar y desapilar son O(1).

*Ejemplo:* Ana tiene la TAB001 y Pedro la TAB003. Pedro devuelve su tablet, pero el encargado
escribe la cédula de Ana. Con *Deshacer*, el préstamo de Ana vuelve a estar activo y se puede
registrar correctamente la devolución de Pedro.

### 6. Lista circular — Turnos de lectura (`ListaCircularTurnos`)

**Cómo funciona:** los lectores se guardan en `NodoTurno`, donde el último nodo apunta al primero.
La lista mantiene la referencia `actual` (quien tiene el turno).
- `avanzar` mueve `actual` al siguiente.
- `eliminarActual` reenlaza el nodo anterior con el siguiente sin romper el círculo.
- `mostrarRonda` usa un `do-while` que se detiene al volver al nodo inicial, para evitar un bucle
  infinito.

**Por qué se eligió:** varios estudiantes comparten una sola tablet de consulta rápida por turnos.
Cuando termina el último, vuelve a empezar el primero. En una lista circular esa rotación es
natural: no hay que detectar el final ni reiniciar el recorrido.

## Casos de prueba

Los casos se ejecutan en orden, desde el inicio del programa con los datos de prueba cargados.

| # | Caso | Pasos en el menú | Resultado esperado | Captura |
| --- | --- | --- | --- | --- |
| 1 | Préstamo exitoso | `2` → cédula `1111111111` → nombre `Ana Perez` → tipo `1` (Estudiante) | `Prestamo registrado: tablet TAB001 (64 GB) asignada a Ana Perez.` | [Ver](Capturas_Ejecucion/Caso_01_Prestamo.png) |
| 2 | Regla de 32 GB | `8` → `1` → `TAB003` (a mantenimiento) → `0`. Luego `2` → cédula `2222222222` → `Pedro Ruiz` → tipo `2` (Grupo de investigación) | `REGLA: TAB004 (16 GB) no puede asignarse a grupos de investigacion (minimo 32 GB).` y `Solicitud enviada a la cola (posicion 1).` | [Ver](Capturas_Ejecucion/Caso_02_Regla32GB.png) |
| 3 | Cola FIFO | `5` → `2` (Pedro aparece en el FRENTE) → `0`. Luego `8` → `2` → `TAB003` → `0`. Luego `5` → `3` | `Solicitud atendida. Prestamo registrado: tablet TAB003 (128 GB) asignada a Pedro Ruiz.` | [Ver](Capturas_Ejecucion/Caso_03_Cola.png) |
| 4 | Deshacer devolución con usuario equivocado | `3` → cédula `1111111111` (se registra por error la devolución de Ana). Luego `9` | `Devolucion deshecha: el prestamo de Ana Perez con la tablet TAB001 vuelve a estar activo.` En la opción `4` Ana vuelve a aparecer. | [Ver](Capturas_Ejecucion/Caso_04_Deshacer.png) |
| 5 | Historial bidireccional | `6` → `1` y luego `6` → `2` | Todas las operaciones anteriores (préstamo, cola, mantenimiento, atención, devolución y deshacer) de la más antigua a la más reciente y al revés. | [Ver](Capturas_Ejecucion/Caso_05_Historial.png) |
| 6 | Turnos circulares | `7` → `1` y agregar `Luis`, `Sara`, `Joel` → `4` → `2` cuatro veces → `3` → `4` | El turno pasa Sara → Joel → **Luis** → Sara (después del último vuelve el primero). Al eliminar a Sara el turno pasa a Joel y la ronda queda `Joel → Luis`. | [Ver](Capturas_Ejecucion/Caso_06_Turnos.png) |
| 7 | Validaciones | `1` → `5` → `TAB002`; `1` → `4` → `TAB009`; en *Prestar* ingresar la cédula `123`; en el menú escribir letras | `ERROR: no se puede eliminar TAB002 porque esta PRESTADA.` / `ERROR: no existe la tablet TAB009.` / `ERROR: la cedula debe tener exactamente 10 digitos numericos.` / `ERROR: ingrese un numero entre 0 y 9.` | [Ver](Capturas_Ejecucion/Caso_07_Validaciones.png) |

### Evidencias de ejecución

Capturas tomadas en la terminal integrada de Visual Studio Code, siguiendo los pasos de la tabla anterior.

**Caso 1 — Préstamo exitoso**

![Caso 1](Capturas_Ejecucion/Caso_01_Prestamo.png)

**Caso 2 — Regla de 32 GB**

![Caso 2](Capturas_Ejecucion/Caso_02_Regla32GB.png)

**Caso 3 — Cola FIFO**

![Caso 3](Capturas_Ejecucion/Caso_03_Cola.png)

**Caso 4 — Deshacer devolución**

![Caso 4](Capturas_Ejecucion/Caso_04_Deshacer.png)

**Caso 5 — Historial bidireccional**

![Caso 5](Capturas_Ejecucion/Caso_05_Historial.png)

**Caso 6 — Turnos circulares**

![Caso 6](Capturas_Ejecucion/Caso_06_Turnos.png)

**Caso 7 — Validaciones**

![Caso 7](Capturas_Ejecucion/Caso_07_Validaciones.png)

El informe completo está en [`Informe/Prueba_02_Estructura_Datos.pdf`](Informe/Prueba_02_Estructura_Datos.pdf).

## Evidencia de colaboración

### Forma de trabajo en GitHub

1. Cada integrante clona el repositorio y crea su rama:
   ```bash
   git clone https://github.com/Damienq7w/Estructura_Datos_Prueba2.git
   cd Estructura_Datos_Prueba2
   git checkout -b nombre-de-su-rama
   ```
2. Sube sus archivos con commits descriptivos desde **su propia cuenta**:
   ```bash
   git add ExamenED_Grupo4/modelo/Tablet.java
   git commit -m "Crea clase Tablet"
   git push origin nombre-de-su-rama
   ```
3. Abre un **Pull Request** hacia `main`. El líder lo revisa e integra.
4. Primero se suben las clases de `modelo/`, luego las estructuras, después la integración
   (`BibliotecaService` y `Main`) y al final la documentación y las capturas.

### Commits de cada integrante

Commits registrados en el repositorio (sin contar los *merge* de Pull Requests):

| Integrante | Usuario | Rama | Commits |
| --- | --- | --- | --- |
| Damian Cunalata | `Damienq7w` | `Damian_Cunalata` / `main` | `Creacion de la estructura del repo` · `Creacion del packaget service y la clase Main` · `Update project name and member responsibilities` · `Update project responsibilities to 'Completado'` · `Creacion de .vscode para que arranque el programa en Main` · `Update README.md` |
| Kenneth Chalco | `KEN3DYY` | `Kenneth_Chalco` | `Primer avance` · `Primera corrección` · `Correcciones finales` · `Correccion` |
| Luis Silva | `luuissilva` | `Luis-Silva` | `Creación de clases Prestamo y NodoPrestamo` · `Agrega busqueda por cedula y eliminación de prestamo en lista simple` · `Nuevos comentarios agregados ListaSimplePrestamos` |
| Joel Tisalema | `Joel03032007` | `Rama-Joel` | `Crear solicitud, Nodo Solicitud` · `Crear solicitud y Nodo Solicitud` · `Implementacion de colaSolicitudes` · `Nodo Accion y Pila deshacer` |
| Mónica Tacuri | `tacurisantillanmonicasara-dot` | `Sara-Tacuri` | `Create Movimiento.java` · `Implementa historial con lista doble` · `Implemente la lista circular de turnos de lectura` · `Implemente Nodo Turno` |
| Josue Jampier Camacho | `jampiercamacho94-cloud` | `rama-Josue` | `Presentación del informe realizado` · `Agrega capturas de casos de prueba 1 al 4` · `Agrega capturas de casos de prueba 5 al 7` |

Cada rama se integró a `main` mediante Pull Request (#1 a #7). El historial completo se puede
verificar en la pestaña **Commits** y en **Insights → Contributors** del repositorio.

![Contribuidores del repositorio](Capturas_Ejecucion/Evidencia_Contributors.png)

### Aporte individual

| Integrante | Aporte |
| --- | --- |
| Damian Cunalata | Creó el repositorio y el README. Desarrolló el menú, las validaciones de entrada y `BibliotecaService`, que integra las seis estructuras y aplica la regla de 32 GB. Integró las ramas en `main`. |
| Kenneth Chalco | Desarrolló la clase `Tablet` y la lista secuencial del inventario, con eliminación validada, búsqueda de tablet disponible según la regla de 32 GB y carga de datos de prueba. |
| Luis Silva | Desarrolló la clase `Prestamo` y la lista simplemente enlazada de préstamos activos, con inserción, búsqueda por cédula, eliminación y recorrido. |
| Joel Tisalema | Desarrolló la clase `Solicitud`, la cola FIFO de solicitudes en espera y la pila LIFO para deshacer la última devolución. |
| Mónica Tacuri | Desarrolló la clase `Movimiento`, la lista doblemente enlazada del historial con recorrido en ambos sentidos y la lista circular de turnos de lectura. |
| Josue Jampier Camacho | Elaboró el informe de la prueba (`Informe/Prueba_02_Estructura_Datos.pdf`), tomó las capturas de ejecución en Visual Studio Code y documentó los casos de prueba. |

## Restricciones respetadas

- Lenguaje Java con JDK 17 y demostración en Visual Studio Code.
- Sin base de datos ni interfaz gráfica: los datos se mantienen en memoria durante la ejecución.
- Estructuras dinámicas implementadas con nodos propios, sin `LinkedList`, `Stack`, `Queue`,
  `ArrayDeque`, `ArrayList` ni otras colecciones de `java.util`. De ese paquete solo se usa
  `Scanner` para leer el teclado.
- Código organizado en `Main.java`, carpeta `modelo/`, clases de estructuras y servicio, con
  nombres significativos y sin errores de compilación.
