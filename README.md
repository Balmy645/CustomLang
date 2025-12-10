# CustomLang
A repertory made to practice the creation of a Lang compilator using ANTLR-3.5.2 and Java. 


# Tabla de lo que soporta la gramática

## 1. Estructura general del programa

| Elemento | ¿Soportado? | Ejemplo |
|---------|-------------|---------|
| Programa con múltiples clases | ✔ | `class A { ... } class B { ... }` |
| Declaración de clase | ✔ | `class MiClase { ... }` |
| Atributos de clase | ✔ | `int x;` |
| Métodos | ✔ | `int f(int a) { ... }` |
| Métodos sin tipo void | ❌ | *(no permitido)* |
| Uso de `return` | ✔ | `return x;` |

---

## 2. Declaraciones

| Elemento | ¿Soportado? | Ejemplo |
|---------|-------------|---------|
| Declaración de variable | ✔ | `int x;` |
| Declaración con asignación | ✔ | `int x = 2;` |
| Múltiples parámetros en métodos | ✔ | `int f(int a, int b)` |
| Tipos soportados | ✔ | `int`, `float`, `string`, etc. |

---

## 3. Asignaciones

| Elemento | ¿Soportado? | Ejemplo |
|---------|-------------|---------|
| Asignación simple | ✔ | `x = 5;` |
| Asignación con expresión | ✔ | `x = a + b * 3;` |
| Acceso a atributos | ✔ | `this.x = 10;` (si está permitido) |
| Llamada de método en asignación | ✔ | `x = f(3);` |

---

## 4. Expresiones

| Elemento | ¿Soportado? | Ejemplo |
|---------|-------------|---------|
| Operadores aritméticos | ✔ | `a + b`, `a - b`, `a * b`, `a / b` |
| Operadores relacionales | ✔ | `a < b`, `a == b` |
| Operadores lógicos | ✔ | `a && b`, `a || b` |
| Paréntesis | ✔ | `(a + b) * c` |
| Llamadas de métodos en expresiones | ✔ | `f(a) + g(b)` |

---

## 5. Estructuras de control

| Estructura | ¿Soportado? | Ejemplo |
|-----------|-------------|---------|
| `if` simple | ✔ | `if (x < 10) { ... }` |
| `if-else` | ✔ | `if (x < 10) { ... } else { ... }` |
| `while` | ✔ | `while (x < 10) { ... }` |
| `do-while` | ✔ | `do { ... } while (x < 10);` |
| `for` | ❌ | *(no soportado)* |
| `switch` | ❌ | *(no soportado)* |

---

## 6. Llamadas de métodos

| Elemento | ¿Soportado? | Ejemplo |
|---------|-------------|---------|
| Llamada simple | ✔ | `f();` |
| Llamada con argumentos | ✔ | `f(a, b);` |
| Llamada dentro de expresión | ✔ | `x = f(a + 1);` |
| Llamada dentro de condición | ✔ | `if (f(a)) { ... }` |

---

## 7. Bloques

| Elemento | ¿Soportado? | Ejemplo |
|---------|-------------|---------|
| Bloques `{ ... }` | ✔ | `{ int x; x = 3; }` |
| Sentencias en bloque | ✔ | declaraciones, asignaciones, if, while, return |

---

## 8. Return

| Elemento | ¿Soportado? | Ejemplo |
|---------|-------------|---------|
| `return expresión;` | ✔ | `return x + 1;` |
| `return;` sin valor | ❌ | *(no soportado)* |

## 9. Sample

```java

public class Sistema {
    int a, b, c;
    double d, e;
    boolean ok;

    int inc(int x) {
        int r;
        r = x + 1;
        return r;
    }

    int doble(int y) {
        int t;
        t = y * 2;
        return t;
    }

    int triple(int z) {
        int k;
        k = z * 3;
        return k;
    }

    int mezcla(int m, int n) {
        int r;
        r = m + n;
        r = r * 2;
        return r;
    }

    int condicional(int x) {
        int r;
        r = x - 4;
        do{
            r = r + 1;
        }while (r < 0);
        return r;
    }

    int nunca() {
        int x;
        x = 10;
        return x;
    }

    int una(int u) {
        int w;
        w = u + 5;
        return w;
    }

    int main(int z) {
        int r;
        int s;
        int t;
        int q;

        r = inc(z);
        s = doble(r);
        t = triple(s);
        q = mezcla(t, r);

        r = condicional(q);

        una(r);

        r = doble(r);
        r = doble(r);

        r = mezcla(r, inc(s));
        s = triple(mezcla(r, s));

        ok = r - s;

        return r;
    }
}
```
