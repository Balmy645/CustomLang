

grammar simple;

@header {
    import java.util.HashMap;
    import java.util.Map;
    import java.util.Set;
}

@members {
    // -----------------------------
    // Tablas y contadores
    // -----------------------------
    public HashMap<String, Integer> TSG = new HashMap<String, Integer>(); // Global: clases, métodos, atributos
    public HashMap<String, Integer> TSL = new HashMap<String, Integer>(); // Local: variables/args del método actual
    public HashMap<String, Integer> TArgs = new HashMap<String, Integer>(); // Args temporales antes de entrar al método

    // Conteo de llamadas a métodos (UNFOLDING)
    public HashMap<String, Integer> metodoLlamadas = new HashMap<String, Integer>();

    // Conteo de errores por método y global
    public HashMap<String, Integer> erroresPorMetodo = new HashMap<String, Integer>();
    public int erroresGlobal = 0;
    public String metodoActual = null;

    private javax.swing.JTextArea salida;

    public void setSalida(javax.swing.JTextArea _salida) {
        salida = _salida;
    }

    // Registra error sintáctico (emitErrorMessage es llamado por ANTLR a errores léx/sint)
    @Override
    public void emitErrorMessage(String msg) {
        registrarError("Error sintáctico: " + msg);
    }

    // Helper para registrar errores (incrementa global y por método si aplica)
    public void registrarError(String msg) {
        if (salida != null) salida.append(msg + "\n");
        erroresGlobal++;
        if (metodoActual != null) {
            Integer v = erroresPorMetodo.get(metodoActual);
            if (v == null) v = 0;
            erroresPorMetodo.put(metodoActual, v + 1);
        }
    }

    // Añade símbolo a la tabla indicada (TSG, TSL, TArgs)
    public void addSymbol(HashMap<String, Integer> tabla, String id, String tipo){
        int code;
        if ("int".equals(tipo)) code = 1;
        else if ("double".equals(tipo)) code = 2;
        else if ("boolean".equals(tipo)) code = 4;
        else code = 3;

        if (!tabla.containsKey(id)) {
            tabla.put(id, code);
            if (salida != null) salida.append("Agregando símbolo: " + id + " tipo: " + tipo + "\n");
        } else {
            registrarError("Error: símbolo redeclarado (" + id + ")");
        }
    }

    // Busca símbolo: primero TSL (local), luego TSG (global)
    public int searchSymbol(String id) {
        Integer existe = TSL.get(id);
        if (existe != null) return existe;
        existe = TSG.get(id);
        if (existe != null) return existe;
        registrarError("ID no declarado: " + id);
        return 3; // error type
    }

    // Imprime tablas (puedes quitar llamadas excesivas si es ruidoso)
    public void printTables() {
        if (salida == null) return;
        salida.append("\n--- Tabla Global (TSG) ---\n");
        for (String key : TSG.keySet()) {
            salida.append(key + " -> " + TSG.get(key) + "\n");
        }
        salida.append("--- Tabla Local (TSL) ---\n");
        for (String key : TSL.keySet()) {
            salida.append(key + " -> " + TSL.get(key) + "\n");
        }
        salida.append("--------------------------\n");
    }

    // Llamada a incrementar contador de llamada de método (si existe en mapa)
    public void registrarLlamadaMetodo(String id) {
        if (id == null) return;
        Integer v = metodoLlamadas.get(id);
        if (v != null) {
            metodoLlamadas.put(id, v + 1);
        } else {
            // si no existe en mapa, no hacemos nada (podría ser llamada a método externo/no declarado)
            // opcional: registrarError("Llamada a método no declarado: " + id);
        }
    }

    // Reporte UNFOLDING: métodos nunca llamados o solo 1 vez al finalizar clase
    public void reporteUnfoldingParaClase(String nombreClase) {
        if (salida == null) return;
        salida.append("\n--- OPTIMIZACIÓN UNFOLDING (Clase: " + nombreClase + ") ---\n");
        Set<Map.Entry<String,Integer>> entries = metodoLlamadas.entrySet();
        for (Map.Entry<String,Integer> e : entries) {
            String metodo = e.getKey();
            int count = e.getValue();
            if (count == 0) {
                salida.append("OPT-UNF-01: Método '" + metodo + "' NUNCA es llamado (optimizable)\n");
            } else if (count == 1) {
                salida.append("OPT-UNF-02: Método '" + metodo + "' es llamado solo UNA VEZ (posible inlining/optimización)\n");
            }
        }
        salida.append("--- FIN OPTIMIZACIÓN UNFOLDING ---\n");
    }
}

// -----------------------------
// Entrada principal
// -----------------------------
program
    : theClass+ {
        if (salida != null) {
            salida.append("\n--- Análisis completado ---\n");
            salida.append("Total de errores en el programa: " + erroresGlobal + "\n");
        }
    }
    ;

// -----------------------------
// Clases y miembros
// -----------------------------
theClass
    : accessModif CLASS id1=ID {
          // registrar clase en TSG (tipo 'class' como code 3 por convención)
          addSymbol(TSG, $id1.text, "class");
          // limpiar mapas de conteo de llamadas para la nueva clase
          // NOTA: asumimos un análisis por clase; reiniciamos metodoLlamadas aquí
          metodoLlamadas.clear();
      }
      OCURLYB member* CCURLYB
      {
          // Al finalizar la clase, emitir reporte UNFOLDING
          reporteUnfoldingParaClase($id1.text);
      }
    ;

// member puede ser método o propiedad
member
    : method
    | property
    ;

// Propiedades globales (atributos) - registradas en TSG
property
    : (accessModif)? t=type id1=ID {
          addSymbol(TSG, $id1.text, $t.text);
      }
      (',' id2=ID { addSymbol(TSG, $id2.text, $t.text); })*
      SEMICOLON
    ;

// -----------------------------
// MÉTODOS
// -----------------------------
method
    : (accessModif)? retType=type id1=ID
        {
            // Registrar método globalmente (TSG) y en contadores de llamadas y errores
            addSymbol(TSG, $id1.text, $retType.text);
            metodoLlamadas.put($id1.text, 0);            // UNFOLDING: inicializa contador de llamadas
            erroresPorMetodo.put($id1.text, 0);          // inicializa contador de errores por método
            metodoActual = $id1.text;                    // establecer el método actual para contabilizar errores
            if (salida != null) salida.append("\n>> Analizando método: " + metodoActual + "\n");
        }
      LPAREN decla_args? RPAREN
      {
          // Al entrar al cuerpo: limpiar tabla local y volcar argumentos
          TSL.clear();
          if (!TArgs.isEmpty()) {
              TSL.putAll(TArgs);
              TArgs.clear();
          }
          // imprimir tabla inicial opcional
          printTables();
      }
      OCURLYB sentences* CCURLYB
      {
          // Al salir del método: reportar errores de este método
          Integer ecount = erroresPorMetodo.get(metodoActual);
          if (ecount == null) ecount = 0;
          if (salida != null) salida.append("<< FIN de método '" + metodoActual + "' — Errores detectados: " + ecount + " (ERR-M)\n");
          // limpiar metodoActual
          metodoActual = null;
      }
    ;

// Declaración de argumentos: se almacenan directamente en TArgs para pasar a TSL después del LPAREN
decla_args
    : t1=type id1=ID { addSymbol(TArgs, $id1.text, $t1.text); }
      (',' t2=type id2=ID { addSymbol(TArgs, $id2.text, $t2.text); })*
    ;

// -----------------------------
// Sentencias y asignaciones
// -----------------------------
sentences
    : private_declar
    | assigment
    | doWhileStmt
    | returnStmt
    | methodCallStmt
    ;


// Variables locales
private_declar
    : t=type id1=ID { addSymbol(TSL, $id1.text, $t.text); }
      (',' id2=ID { addSymbol(TSL, $id2.text, $t.text); })*
      SEMICOLON
    ;

// Asignación: además BTA (si expr.reducible => report OPT-BTA-03)
assigment
    : id1=ID '=' e=expr SEMICOLON
      {
          if ($e.eType == 3) {
              registrarError("ERR-M: tipo incompatible en la asignación de " + $id1.text);
          } else {
              if (salida != null) salida.append("Tipo de expr es: " + $e.eType + "\n");
              // Si la expresión es reducible en tiempo de compilación
              if ($e.reducible) {
                  if (salida != null) salida.append("OPT-BTA-03: La expresión asignada a '" + $id1.text + "' puede reducirse en compilación\n");
              }
          }
      }
    ;

// -----------------------------
// EXPRESIONES (BTA: eType + reducible)
// -----------------------------
// expr devuelve eType y si es reducible (true si toda la subexpresión es constante)
expr returns [int eType, boolean reducible]
    : m1=multExpr {
          $eType = $m1.mType;
          $reducible = $m1.reducible;
      }
      ( op=('+' | '-') m2=multExpr {
          if ($eType != $m2.mType) {
              $eType = 3;
              registrarError("Error: tipos incompatibles en la expresión (+/-)");
          }
          // Reducible sólo si ambos operandos eran reducibles
          $reducible = $reducible && $m2.reducible;
      })*
    ;

returnStmt
    : RETURN SEMICOLON
    | RETURN expr SEMICOLON
    ;


// multExpr devuelve mType y reducible
multExpr returns [int mType, boolean reducible]
    : a1=atom {
          $mType = $a1.aType;
          $reducible = $a1.reducible;
      }
      ( op=('*' | '/') a2=atom {
          if ($mType != $a2.aType) {
              $mType = 3;
              registrarError("Error: tipos incompatibles en la expresión (*,/)");
          }
          $reducible = $reducible && $a2.reducible;
      })*
    ;

// atom ahora incluye llamadas a métodos (ID '(' listaArgs? ')') y mantiene flag reducible
atom returns [int aType, boolean reducible]
    : CINT {
          $aType = 1;
          $reducible = true;   // constante entera -> reducible
      }
    | CFLOAT {
          $aType = 2;
          $reducible = true;   // constante float -> reducible
      }
    | idcall=ID LPAREN listaArgs? RPAREN {
          // Llamada a método: registrar llamada (UNFOLDING)
          registrarLlamadaMetodo($idcall.text);
          // Tipo resultante: buscamos tipo de retorno del método en TSG
          $aType = searchSymbol($idcall.text);
          $reducible = false; // llamas a función: por defecto no reducible en compilación
      }
    | id=ID {
          $aType = searchSymbol($id.text);
          $reducible = false; // variable o uso de ID no es reducible
      }
    | LPAREN e=expr RPAREN {
          $aType = $e.eType;
          $reducible = $e.reducible;
      }
    ;

// lista de argumentos (puede usarse para llamadas a método)
listaArgs
    : e1=expr (',' e2=expr)*
    ;

// -----------------------------
// Expresiones relacionales (devuelven rType: 4=boolean, 3=error)
// -----------------------------
relExpr returns [int rType]
    : a1=expr op=('>' | '<' | '>=' | '<=' | '==' | '!=') a2=expr {
          if ($a1.eType == 3 || $a2.eType == 3) {
              $rType = 3;
              registrarError("Error: operandos inválidos en comparación");
          } else {
              $rType = 4; // boolean
          }
      }
    ;

// -----------------------------
// DO-WHILE (valida condición booleana)
// -----------------------------
doWhileStmt
    : DO OCURLYB sentences* CCURLYB WHILE LPAREN cond=relExpr RPAREN SEMICOLON {
          if ($cond.rType != 4) {
              registrarError("Error: condición de do-while no es booleana");
          } else {
              if (salida != null) salida.append("Condición booleana válida en do-while\n");
          }
      }
    ;
// ---------------------------
// METHOD-CALL (valida la llamada de un metodo)
// ----------------------------    
methodCallStmt
    : idcall=ID LPAREN listaArgs? RPAREN SEMICOLON {
          registrarLlamadaMetodo($idcall.text);
      }
    ;


// -----------------------------
// Tokens y léxicos
// -----------------------------
accessModif : PUBLIC | PRIVATE | PROTECTED ;
type        : INT | DOUBLE | BOOLEAN ;
CLASS       : 'class' ;
INT         : 'int' ;
DOUBLE      : 'double' ;
BOOLEAN     : 'boolean' ;
PUBLIC      : 'public' ;
PRIVATE     : 'private' ;
PROTECTED   : 'protected' ;
DO          : 'do' ;
WHILE       : 'while' ;
RETURN      : 'return' ;
OCURLYB     : '{' ;
CCURLYB     : '}' ;
LPAREN      : '(' ;
RPAREN      : ')' ;
SEMICOLON   : ';' ;
CFLOAT      : CINT DOT CINT ;
CINT        : ('0'..'9')+ ;
ID          : ('a'..'z' | 'A'..'Z' | '_' ) ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')* ;
DOT         : '.' ;
WS          : (' ' | '\n' | '\t' | '\r')+ { $channel=HIDDEN; } ;