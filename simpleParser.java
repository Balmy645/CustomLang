// $ANTLR 3.5.2 simple.g 2025-12-09 18:08:50

    import java.util.HashMap;
    import java.util.Map;
    import java.util.Set;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class simpleParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "BOOLEAN", "CCURLYB", "CFLOAT", 
		"CINT", "CLASS", "DO", "DOT", "DOUBLE", "ID", "INT", "LPAREN", "OCURLYB", 
		"PRIVATE", "PROTECTED", "PUBLIC", "RETURN", "RPAREN", "SEMICOLON", "WHILE", 
		"WS", "'!='", "'*'", "'+'", "','", "'-'", "'/'", "'<'", "'<='", "'='", 
		"'=='", "'>'", "'>='"
	};
	public static final int EOF=-1;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int T__34=34;
	public static final int T__35=35;
	public static final int BOOLEAN=4;
	public static final int CCURLYB=5;
	public static final int CFLOAT=6;
	public static final int CINT=7;
	public static final int CLASS=8;
	public static final int DO=9;
	public static final int DOT=10;
	public static final int DOUBLE=11;
	public static final int ID=12;
	public static final int INT=13;
	public static final int LPAREN=14;
	public static final int OCURLYB=15;
	public static final int PRIVATE=16;
	public static final int PROTECTED=17;
	public static final int PUBLIC=18;
	public static final int RETURN=19;
	public static final int RPAREN=20;
	public static final int SEMICOLON=21;
	public static final int WHILE=22;
	public static final int WS=23;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public simpleParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public simpleParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return simpleParser.tokenNames; }
	@Override public String getGrammarFileName() { return "simple.g"; }


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



	// $ANTLR start "program"
	// simple.g:123:1: program : ( theClass )+ ;
	public final void program() throws RecognitionException {
		try {
			// simple.g:124:5: ( ( theClass )+ )
			// simple.g:124:7: ( theClass )+
			{
			// simple.g:124:7: ( theClass )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= PRIVATE && LA1_0 <= PUBLIC)) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// simple.g:124:7: theClass
					{
					pushFollow(FOLLOW_theClass_in_program31);
					theClass();
					state._fsp--;

					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}


			        if (salida != null) {
			            salida.append("\n--- Análisis completado ---\n");
			            salida.append("Total de errores en el programa: " + erroresGlobal + "\n");
			        }
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "program"



	// $ANTLR start "theClass"
	// simple.g:135:1: theClass : accessModif CLASS id1= ID OCURLYB ( member )* CCURLYB ;
	public final void theClass() throws RecognitionException {
		Token id1=null;

		try {
			// simple.g:136:5: ( accessModif CLASS id1= ID OCURLYB ( member )* CCURLYB )
			// simple.g:136:7: accessModif CLASS id1= ID OCURLYB ( member )* CCURLYB
			{
			pushFollow(FOLLOW_accessModif_in_theClass54);
			accessModif();
			state._fsp--;

			match(input,CLASS,FOLLOW_CLASS_in_theClass56); 
			id1=(Token)match(input,ID,FOLLOW_ID_in_theClass60); 

			          // registrar clase en TSG (tipo 'class' como code 3 por convención)
			          addSymbol(TSG, (id1!=null?id1.getText():null), "class");
			          // limpiar mapas de conteo de llamadas para la nueva clase
			          // NOTA: asumimos un análisis por clase; reiniciamos metodoLlamadas aquí
			          metodoLlamadas.clear();
			      
			match(input,OCURLYB,FOLLOW_OCURLYB_in_theClass70); 
			// simple.g:143:15: ( member )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==BOOLEAN||LA2_0==DOUBLE||LA2_0==INT||(LA2_0 >= PRIVATE && LA2_0 <= PUBLIC)) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// simple.g:143:15: member
					{
					pushFollow(FOLLOW_member_in_theClass72);
					member();
					state._fsp--;

					}
					break;

				default :
					break loop2;
				}
			}

			match(input,CCURLYB,FOLLOW_CCURLYB_in_theClass75); 

			          // Al finalizar la clase, emitir reporte UNFOLDING
			          reporteUnfoldingParaClase((id1!=null?id1.getText():null));
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "theClass"



	// $ANTLR start "member"
	// simple.g:151:1: member : ( method | property );
	public final void member() throws RecognitionException {
		try {
			// simple.g:152:5: ( method | property )
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( ((LA3_0 >= PRIVATE && LA3_0 <= PUBLIC)) ) {
				int LA3_1 = input.LA(2);
				if ( (LA3_1==BOOLEAN||LA3_1==DOUBLE||LA3_1==INT) ) {
					int LA3_2 = input.LA(3);
					if ( (LA3_2==ID) ) {
						int LA3_3 = input.LA(4);
						if ( (LA3_3==LPAREN) ) {
							alt3=1;
						}
						else if ( (LA3_3==SEMICOLON||LA3_3==27) ) {
							alt3=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 3, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 3, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 3, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA3_0==BOOLEAN||LA3_0==DOUBLE||LA3_0==INT) ) {
				int LA3_2 = input.LA(2);
				if ( (LA3_2==ID) ) {
					int LA3_3 = input.LA(3);
					if ( (LA3_3==LPAREN) ) {
						alt3=1;
					}
					else if ( (LA3_3==SEMICOLON||LA3_3==27) ) {
						alt3=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 3, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 3, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}

			switch (alt3) {
				case 1 :
					// simple.g:152:7: method
					{
					pushFollow(FOLLOW_method_in_member101);
					method();
					state._fsp--;

					}
					break;
				case 2 :
					// simple.g:153:7: property
					{
					pushFollow(FOLLOW_property_in_member109);
					property();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "member"



	// $ANTLR start "property"
	// simple.g:157:1: property : ( accessModif )? t= type id1= ID ( ',' id2= ID )* SEMICOLON ;
	public final void property() throws RecognitionException {
		Token id1=null;
		Token id2=null;
		ParserRuleReturnScope t =null;

		try {
			// simple.g:158:5: ( ( accessModif )? t= type id1= ID ( ',' id2= ID )* SEMICOLON )
			// simple.g:158:7: ( accessModif )? t= type id1= ID ( ',' id2= ID )* SEMICOLON
			{
			// simple.g:158:7: ( accessModif )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( ((LA4_0 >= PRIVATE && LA4_0 <= PUBLIC)) ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// simple.g:158:8: accessModif
					{
					pushFollow(FOLLOW_accessModif_in_property128);
					accessModif();
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_type_in_property134);
			t=type();
			state._fsp--;

			id1=(Token)match(input,ID,FOLLOW_ID_in_property138); 

			          addSymbol(TSG, (id1!=null?id1.getText():null), (t!=null?input.toString(t.start,t.stop):null));
			      
			// simple.g:161:7: ( ',' id2= ID )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==27) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// simple.g:161:8: ',' id2= ID
					{
					match(input,27,FOLLOW_27_in_property149); 
					id2=(Token)match(input,ID,FOLLOW_ID_in_property153); 
					 addSymbol(TSG, (id2!=null?id2.getText():null), (t!=null?input.toString(t.start,t.stop):null)); 
					}
					break;

				default :
					break loop5;
				}
			}

			match(input,SEMICOLON,FOLLOW_SEMICOLON_in_property165); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "property"



	// $ANTLR start "method"
	// simple.g:168:1: method : ( accessModif )? retType= type id1= ID LPAREN ( decla_args )? RPAREN OCURLYB ( sentences )* CCURLYB ;
	public final void method() throws RecognitionException {
		Token id1=null;
		ParserRuleReturnScope retType =null;

		try {
			// simple.g:169:5: ( ( accessModif )? retType= type id1= ID LPAREN ( decla_args )? RPAREN OCURLYB ( sentences )* CCURLYB )
			// simple.g:169:7: ( accessModif )? retType= type id1= ID LPAREN ( decla_args )? RPAREN OCURLYB ( sentences )* CCURLYB
			{
			// simple.g:169:7: ( accessModif )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( ((LA6_0 >= PRIVATE && LA6_0 <= PUBLIC)) ) {
				alt6=1;
			}
			switch (alt6) {
				case 1 :
					// simple.g:169:8: accessModif
					{
					pushFollow(FOLLOW_accessModif_in_method186);
					accessModif();
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_type_in_method192);
			retType=type();
			state._fsp--;

			id1=(Token)match(input,ID,FOLLOW_ID_in_method196); 

			            // Registrar método globalmente (TSG) y en contadores de llamadas y errores
			            addSymbol(TSG, (id1!=null?id1.getText():null), (retType!=null?input.toString(retType.start,retType.stop):null));
			            metodoLlamadas.put((id1!=null?id1.getText():null), 0);            // UNFOLDING: inicializa contador de llamadas
			            erroresPorMetodo.put((id1!=null?id1.getText():null), 0);          // inicializa contador de errores por método
			            metodoActual = (id1!=null?id1.getText():null);                    // establecer el método actual para contabilizar errores
			            if (salida != null) salida.append("\n>> Analizando método: " + metodoActual + "\n");
			        
			match(input,LPAREN,FOLLOW_LPAREN_in_method214); 
			// simple.g:178:14: ( decla_args )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==BOOLEAN||LA7_0==DOUBLE||LA7_0==INT) ) {
				alt7=1;
			}
			switch (alt7) {
				case 1 :
					// simple.g:178:14: decla_args
					{
					pushFollow(FOLLOW_decla_args_in_method216);
					decla_args();
					state._fsp--;

					}
					break;

			}

			match(input,RPAREN,FOLLOW_RPAREN_in_method219); 

			          // Al entrar al cuerpo: limpiar tabla local y volcar argumentos
			          TSL.clear();
			          if (!TArgs.isEmpty()) {
			              TSL.putAll(TArgs);
			              TArgs.clear();
			          }
			          // imprimir tabla inicial opcional
			          printTables();
			      
			match(input,OCURLYB,FOLLOW_OCURLYB_in_method235); 
			// simple.g:189:15: ( sentences )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0==BOOLEAN||LA8_0==DO||(LA8_0 >= DOUBLE && LA8_0 <= INT)||LA8_0==RETURN) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// simple.g:189:15: sentences
					{
					pushFollow(FOLLOW_sentences_in_method237);
					sentences();
					state._fsp--;

					}
					break;

				default :
					break loop8;
				}
			}

			match(input,CCURLYB,FOLLOW_CCURLYB_in_method240); 

			          // Al salir del método: reportar errores de este método
			          Integer ecount = erroresPorMetodo.get(metodoActual);
			          if (ecount == null) ecount = 0;
			          if (salida != null) salida.append("<< FIN de método '" + metodoActual + "' — Errores detectados: " + ecount + " (ERR-M)\n");
			          // limpiar metodoActual
			          metodoActual = null;
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "method"



	// $ANTLR start "decla_args"
	// simple.g:201:1: decla_args : t1= type id1= ID ( ',' t2= type id2= ID )* ;
	public final void decla_args() throws RecognitionException {
		Token id1=null;
		Token id2=null;
		ParserRuleReturnScope t1 =null;
		ParserRuleReturnScope t2 =null;

		try {
			// simple.g:202:5: (t1= type id1= ID ( ',' t2= type id2= ID )* )
			// simple.g:202:7: t1= type id1= ID ( ',' t2= type id2= ID )*
			{
			pushFollow(FOLLOW_type_in_decla_args268);
			t1=type();
			state._fsp--;

			id1=(Token)match(input,ID,FOLLOW_ID_in_decla_args272); 
			 addSymbol(TArgs, (id1!=null?id1.getText():null), (t1!=null?input.toString(t1.start,t1.stop):null)); 
			// simple.g:203:7: ( ',' t2= type id2= ID )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0==27) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// simple.g:203:8: ',' t2= type id2= ID
					{
					match(input,27,FOLLOW_27_in_decla_args283); 
					pushFollow(FOLLOW_type_in_decla_args287);
					t2=type();
					state._fsp--;

					id2=(Token)match(input,ID,FOLLOW_ID_in_decla_args291); 
					 addSymbol(TArgs, (id2!=null?id2.getText():null), (t2!=null?input.toString(t2.start,t2.stop):null)); 
					}
					break;

				default :
					break loop9;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "decla_args"



	// $ANTLR start "sentences"
	// simple.g:209:1: sentences : ( private_declar | assigment | doWhileStmt | returnStmt | methodCallStmt );
	public final void sentences() throws RecognitionException {
		try {
			// simple.g:210:5: ( private_declar | assigment | doWhileStmt | returnStmt | methodCallStmt )
			int alt10=5;
			switch ( input.LA(1) ) {
			case BOOLEAN:
			case DOUBLE:
			case INT:
				{
				alt10=1;
				}
				break;
			case ID:
				{
				int LA10_2 = input.LA(2);
				if ( (LA10_2==32) ) {
					alt10=2;
				}
				else if ( (LA10_2==LPAREN) ) {
					alt10=5;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 10, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case DO:
				{
				alt10=3;
				}
				break;
			case RETURN:
				{
				alt10=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}
			switch (alt10) {
				case 1 :
					// simple.g:210:7: private_declar
					{
					pushFollow(FOLLOW_private_declar_in_sentences315);
					private_declar();
					state._fsp--;

					}
					break;
				case 2 :
					// simple.g:211:7: assigment
					{
					pushFollow(FOLLOW_assigment_in_sentences323);
					assigment();
					state._fsp--;

					}
					break;
				case 3 :
					// simple.g:212:7: doWhileStmt
					{
					pushFollow(FOLLOW_doWhileStmt_in_sentences331);
					doWhileStmt();
					state._fsp--;

					}
					break;
				case 4 :
					// simple.g:213:7: returnStmt
					{
					pushFollow(FOLLOW_returnStmt_in_sentences339);
					returnStmt();
					state._fsp--;

					}
					break;
				case 5 :
					// simple.g:214:7: methodCallStmt
					{
					pushFollow(FOLLOW_methodCallStmt_in_sentences347);
					methodCallStmt();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "sentences"



	// $ANTLR start "private_declar"
	// simple.g:219:1: private_declar : t= type id1= ID ( ',' id2= ID )* SEMICOLON ;
	public final void private_declar() throws RecognitionException {
		Token id1=null;
		Token id2=null;
		ParserRuleReturnScope t =null;

		try {
			// simple.g:220:5: (t= type id1= ID ( ',' id2= ID )* SEMICOLON )
			// simple.g:220:7: t= type id1= ID ( ',' id2= ID )* SEMICOLON
			{
			pushFollow(FOLLOW_type_in_private_declar368);
			t=type();
			state._fsp--;

			id1=(Token)match(input,ID,FOLLOW_ID_in_private_declar372); 
			 addSymbol(TSL, (id1!=null?id1.getText():null), (t!=null?input.toString(t.start,t.stop):null)); 
			// simple.g:221:7: ( ',' id2= ID )*
			loop11:
			while (true) {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( (LA11_0==27) ) {
					alt11=1;
				}

				switch (alt11) {
				case 1 :
					// simple.g:221:8: ',' id2= ID
					{
					match(input,27,FOLLOW_27_in_private_declar383); 
					id2=(Token)match(input,ID,FOLLOW_ID_in_private_declar387); 
					 addSymbol(TSL, (id2!=null?id2.getText():null), (t!=null?input.toString(t.start,t.stop):null)); 
					}
					break;

				default :
					break loop11;
				}
			}

			match(input,SEMICOLON,FOLLOW_SEMICOLON_in_private_declar399); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "private_declar"



	// $ANTLR start "assigment"
	// simple.g:226:1: assigment : id1= ID '=' e= expr SEMICOLON ;
	public final void assigment() throws RecognitionException {
		Token id1=null;
		ParserRuleReturnScope e =null;

		try {
			// simple.g:227:5: (id1= ID '=' e= expr SEMICOLON )
			// simple.g:227:7: id1= ID '=' e= expr SEMICOLON
			{
			id1=(Token)match(input,ID,FOLLOW_ID_in_assigment419); 
			match(input,32,FOLLOW_32_in_assigment421); 
			pushFollow(FOLLOW_expr_in_assigment425);
			e=expr();
			state._fsp--;

			match(input,SEMICOLON,FOLLOW_SEMICOLON_in_assigment427); 

			          if ((e!=null?((simpleParser.expr_return)e).eType:0) == 3) {
			              registrarError("ERR-M: tipo incompatible en la asignación de " + (id1!=null?id1.getText():null));
			          } else {
			              if (salida != null) salida.append("Tipo de expr es: " + (e!=null?((simpleParser.expr_return)e).eType:0) + "\n");
			              // Si la expresión es reducible en tiempo de compilación
			              if ((e!=null?((simpleParser.expr_return)e).reducible:false)) {
			                  if (salida != null) salida.append("OPT-BTA-03: La expresión asignada a '" + (id1!=null?id1.getText():null) + "' puede reducirse en compilación\n");
			              }
			          }
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "assigment"


	public static class expr_return extends ParserRuleReturnScope {
		public int eType;
		public boolean reducible;
	};


	// $ANTLR start "expr"
	// simple.g:245:1: expr returns [int eType, boolean reducible] : m1= multExpr (op= ( '+' | '-' ) m2= multExpr )* ;
	public final simpleParser.expr_return expr() throws RecognitionException {
		simpleParser.expr_return retval = new simpleParser.expr_return();
		retval.start = input.LT(1);

		Token op=null;
		ParserRuleReturnScope m1 =null;
		ParserRuleReturnScope m2 =null;

		try {
			// simple.g:246:5: (m1= multExpr (op= ( '+' | '-' ) m2= multExpr )* )
			// simple.g:246:7: m1= multExpr (op= ( '+' | '-' ) m2= multExpr )*
			{
			pushFollow(FOLLOW_multExpr_in_expr462);
			m1=multExpr();
			state._fsp--;


			          retval.eType = (m1!=null?((simpleParser.multExpr_return)m1).mType:0);
			          retval.reducible = (m1!=null?((simpleParser.multExpr_return)m1).reducible:false);
			      
			// simple.g:250:7: (op= ( '+' | '-' ) m2= multExpr )*
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( (LA12_0==26||LA12_0==28) ) {
					alt12=1;
				}

				switch (alt12) {
				case 1 :
					// simple.g:250:9: op= ( '+' | '-' ) m2= multExpr
					{
					op=input.LT(1);
					if ( input.LA(1)==26||input.LA(1)==28 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_multExpr_in_expr486);
					m2=multExpr();
					state._fsp--;


					          if (retval.eType != (m2!=null?((simpleParser.multExpr_return)m2).mType:0)) {
					              retval.eType = 3;
					              registrarError("Error: tipos incompatibles en la expresión (+/-)");
					          }
					          // Reducible sólo si ambos operandos eran reducibles
					          retval.reducible = retval.reducible && (m2!=null?((simpleParser.multExpr_return)m2).reducible:false);
					      
					}
					break;

				default :
					break loop12;
				}
			}

			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expr"



	// $ANTLR start "returnStmt"
	// simple.g:260:1: returnStmt : ( RETURN SEMICOLON | RETURN expr SEMICOLON );
	public final void returnStmt() throws RecognitionException {
		try {
			// simple.g:261:5: ( RETURN SEMICOLON | RETURN expr SEMICOLON )
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==RETURN) ) {
				int LA13_1 = input.LA(2);
				if ( (LA13_1==SEMICOLON) ) {
					alt13=1;
				}
				else if ( ((LA13_1 >= CFLOAT && LA13_1 <= CINT)||LA13_1==ID||LA13_1==LPAREN) ) {
					alt13=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 13, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}

			switch (alt13) {
				case 1 :
					// simple.g:261:7: RETURN SEMICOLON
					{
					match(input,RETURN,FOLLOW_RETURN_in_returnStmt507); 
					match(input,SEMICOLON,FOLLOW_SEMICOLON_in_returnStmt509); 
					}
					break;
				case 2 :
					// simple.g:262:7: RETURN expr SEMICOLON
					{
					match(input,RETURN,FOLLOW_RETURN_in_returnStmt517); 
					pushFollow(FOLLOW_expr_in_returnStmt519);
					expr();
					state._fsp--;

					match(input,SEMICOLON,FOLLOW_SEMICOLON_in_returnStmt521); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "returnStmt"


	public static class multExpr_return extends ParserRuleReturnScope {
		public int mType;
		public boolean reducible;
	};


	// $ANTLR start "multExpr"
	// simple.g:267:1: multExpr returns [int mType, boolean reducible] : a1= atom (op= ( '*' | '/' ) a2= atom )* ;
	public final simpleParser.multExpr_return multExpr() throws RecognitionException {
		simpleParser.multExpr_return retval = new simpleParser.multExpr_return();
		retval.start = input.LT(1);

		Token op=null;
		ParserRuleReturnScope a1 =null;
		ParserRuleReturnScope a2 =null;

		try {
			// simple.g:268:5: (a1= atom (op= ( '*' | '/' ) a2= atom )* )
			// simple.g:268:7: a1= atom (op= ( '*' | '/' ) a2= atom )*
			{
			pushFollow(FOLLOW_atom_in_multExpr546);
			a1=atom();
			state._fsp--;


			          retval.mType = (a1!=null?((simpleParser.atom_return)a1).aType:0);
			          retval.reducible = (a1!=null?((simpleParser.atom_return)a1).reducible:false);
			      
			// simple.g:272:7: (op= ( '*' | '/' ) a2= atom )*
			loop14:
			while (true) {
				int alt14=2;
				int LA14_0 = input.LA(1);
				if ( (LA14_0==25||LA14_0==29) ) {
					alt14=1;
				}

				switch (alt14) {
				case 1 :
					// simple.g:272:9: op= ( '*' | '/' ) a2= atom
					{
					op=input.LT(1);
					if ( input.LA(1)==25||input.LA(1)==29 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_atom_in_multExpr570);
					a2=atom();
					state._fsp--;


					          if (retval.mType != (a2!=null?((simpleParser.atom_return)a2).aType:0)) {
					              retval.mType = 3;
					              registrarError("Error: tipos incompatibles en la expresión (*,/)");
					          }
					          retval.reducible = retval.reducible && (a2!=null?((simpleParser.atom_return)a2).reducible:false);
					      
					}
					break;

				default :
					break loop14;
				}
			}

			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "multExpr"


	public static class atom_return extends ParserRuleReturnScope {
		public int aType;
		public boolean reducible;
	};


	// $ANTLR start "atom"
	// simple.g:282:1: atom returns [int aType, boolean reducible] : ( CINT | CFLOAT |idcall= ID LPAREN ( listaArgs )? RPAREN |id= ID | LPAREN e= expr RPAREN );
	public final simpleParser.atom_return atom() throws RecognitionException {
		simpleParser.atom_return retval = new simpleParser.atom_return();
		retval.start = input.LT(1);

		Token idcall=null;
		Token id=null;
		ParserRuleReturnScope e =null;

		try {
			// simple.g:283:5: ( CINT | CFLOAT |idcall= ID LPAREN ( listaArgs )? RPAREN |id= ID | LPAREN e= expr RPAREN )
			int alt16=5;
			switch ( input.LA(1) ) {
			case CINT:
				{
				alt16=1;
				}
				break;
			case CFLOAT:
				{
				alt16=2;
				}
				break;
			case ID:
				{
				int LA16_3 = input.LA(2);
				if ( (LA16_3==LPAREN) ) {
					alt16=3;
				}
				else if ( ((LA16_3 >= RPAREN && LA16_3 <= SEMICOLON)||(LA16_3 >= 24 && LA16_3 <= 31)||(LA16_3 >= 33 && LA16_3 <= 35)) ) {
					alt16=4;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 16, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case LPAREN:
				{
				alt16=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 16, 0, input);
				throw nvae;
			}
			switch (alt16) {
				case 1 :
					// simple.g:283:7: CINT
					{
					match(input,CINT,FOLLOW_CINT_in_atom596); 

					          retval.aType = 1;
					          retval.reducible = true;   // constante entera -> reducible
					      
					}
					break;
				case 2 :
					// simple.g:287:7: CFLOAT
					{
					match(input,CFLOAT,FOLLOW_CFLOAT_in_atom606); 

					          retval.aType = 2;
					          retval.reducible = true;   // constante float -> reducible
					      
					}
					break;
				case 3 :
					// simple.g:291:7: idcall= ID LPAREN ( listaArgs )? RPAREN
					{
					idcall=(Token)match(input,ID,FOLLOW_ID_in_atom618); 
					match(input,LPAREN,FOLLOW_LPAREN_in_atom620); 
					// simple.g:291:24: ( listaArgs )?
					int alt15=2;
					int LA15_0 = input.LA(1);
					if ( ((LA15_0 >= CFLOAT && LA15_0 <= CINT)||LA15_0==ID||LA15_0==LPAREN) ) {
						alt15=1;
					}
					switch (alt15) {
						case 1 :
							// simple.g:291:24: listaArgs
							{
							pushFollow(FOLLOW_listaArgs_in_atom622);
							listaArgs();
							state._fsp--;

							}
							break;

					}

					match(input,RPAREN,FOLLOW_RPAREN_in_atom625); 

					          // Llamada a método: registrar llamada (UNFOLDING)
					          registrarLlamadaMetodo((idcall!=null?idcall.getText():null));
					          // Tipo resultante: buscamos tipo de retorno del método en TSG
					          retval.aType = searchSymbol((idcall!=null?idcall.getText():null));
					          retval.reducible = false; // llamas a función: por defecto no reducible en compilación
					      
					}
					break;
				case 4 :
					// simple.g:298:7: id= ID
					{
					id=(Token)match(input,ID,FOLLOW_ID_in_atom637); 

					          retval.aType = searchSymbol((id!=null?id.getText():null));
					          retval.reducible = false; // variable o uso de ID no es reducible
					      
					}
					break;
				case 5 :
					// simple.g:302:7: LPAREN e= expr RPAREN
					{
					match(input,LPAREN,FOLLOW_LPAREN_in_atom647); 
					pushFollow(FOLLOW_expr_in_atom651);
					e=expr();
					state._fsp--;

					match(input,RPAREN,FOLLOW_RPAREN_in_atom653); 

					          retval.aType = (e!=null?((simpleParser.expr_return)e).eType:0);
					          retval.reducible = (e!=null?((simpleParser.expr_return)e).reducible:false);
					      
					}
					break;

			}
			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "atom"



	// $ANTLR start "listaArgs"
	// simple.g:309:1: listaArgs : e1= expr ( ',' e2= expr )* ;
	public final void listaArgs() throws RecognitionException {
		ParserRuleReturnScope e1 =null;
		ParserRuleReturnScope e2 =null;

		try {
			// simple.g:310:5: (e1= expr ( ',' e2= expr )* )
			// simple.g:310:7: e1= expr ( ',' e2= expr )*
			{
			pushFollow(FOLLOW_expr_in_listaArgs675);
			e1=expr();
			state._fsp--;

			// simple.g:310:15: ( ',' e2= expr )*
			loop17:
			while (true) {
				int alt17=2;
				int LA17_0 = input.LA(1);
				if ( (LA17_0==27) ) {
					alt17=1;
				}

				switch (alt17) {
				case 1 :
					// simple.g:310:16: ',' e2= expr
					{
					match(input,27,FOLLOW_27_in_listaArgs678); 
					pushFollow(FOLLOW_expr_in_listaArgs682);
					e2=expr();
					state._fsp--;

					}
					break;

				default :
					break loop17;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "listaArgs"



	// $ANTLR start "relExpr"
	// simple.g:316:1: relExpr returns [int rType] : a1= expr op= ( '>' | '<' | '>=' | '<=' | '==' | '!=' ) a2= expr ;
	public final int relExpr() throws RecognitionException {
		int rType = 0;


		Token op=null;
		ParserRuleReturnScope a1 =null;
		ParserRuleReturnScope a2 =null;

		try {
			// simple.g:317:5: (a1= expr op= ( '>' | '<' | '>=' | '<=' | '==' | '!=' ) a2= expr )
			// simple.g:317:7: a1= expr op= ( '>' | '<' | '>=' | '<=' | '==' | '!=' ) a2= expr
			{
			pushFollow(FOLLOW_expr_in_relExpr710);
			a1=expr();
			state._fsp--;

			op=input.LT(1);
			if ( input.LA(1)==24||(input.LA(1) >= 30 && input.LA(1) <= 31)||(input.LA(1) >= 33 && input.LA(1) <= 35) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			pushFollow(FOLLOW_expr_in_relExpr740);
			a2=expr();
			state._fsp--;


			          if ((a1!=null?((simpleParser.expr_return)a1).eType:0) == 3 || (a2!=null?((simpleParser.expr_return)a2).eType:0) == 3) {
			              rType = 3;
			              registrarError("Error: operandos inválidos en comparación");
			          } else {
			              rType = 4; // boolean
			          }
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return rType;
	}
	// $ANTLR end "relExpr"



	// $ANTLR start "doWhileStmt"
	// simple.g:330:1: doWhileStmt : DO OCURLYB ( sentences )* CCURLYB WHILE LPAREN cond= relExpr RPAREN SEMICOLON ;
	public final void doWhileStmt() throws RecognitionException {
		int cond =0;

		try {
			// simple.g:331:5: ( DO OCURLYB ( sentences )* CCURLYB WHILE LPAREN cond= relExpr RPAREN SEMICOLON )
			// simple.g:331:7: DO OCURLYB ( sentences )* CCURLYB WHILE LPAREN cond= relExpr RPAREN SEMICOLON
			{
			match(input,DO,FOLLOW_DO_in_doWhileStmt762); 
			match(input,OCURLYB,FOLLOW_OCURLYB_in_doWhileStmt764); 
			// simple.g:331:18: ( sentences )*
			loop18:
			while (true) {
				int alt18=2;
				int LA18_0 = input.LA(1);
				if ( (LA18_0==BOOLEAN||LA18_0==DO||(LA18_0 >= DOUBLE && LA18_0 <= INT)||LA18_0==RETURN) ) {
					alt18=1;
				}

				switch (alt18) {
				case 1 :
					// simple.g:331:18: sentences
					{
					pushFollow(FOLLOW_sentences_in_doWhileStmt766);
					sentences();
					state._fsp--;

					}
					break;

				default :
					break loop18;
				}
			}

			match(input,CCURLYB,FOLLOW_CCURLYB_in_doWhileStmt769); 
			match(input,WHILE,FOLLOW_WHILE_in_doWhileStmt771); 
			match(input,LPAREN,FOLLOW_LPAREN_in_doWhileStmt773); 
			pushFollow(FOLLOW_relExpr_in_doWhileStmt777);
			cond=relExpr();
			state._fsp--;

			match(input,RPAREN,FOLLOW_RPAREN_in_doWhileStmt779); 
			match(input,SEMICOLON,FOLLOW_SEMICOLON_in_doWhileStmt781); 

			          if (cond != 4) {
			              registrarError("Error: condición de do-while no es booleana");
			          } else {
			              if (salida != null) salida.append("Condición booleana válida en do-while\n");
			          }
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "doWhileStmt"



	// $ANTLR start "methodCallStmt"
	// simple.g:342:1: methodCallStmt : idcall= ID LPAREN ( listaArgs )? RPAREN SEMICOLON ;
	public final void methodCallStmt() throws RecognitionException {
		Token idcall=null;

		try {
			// simple.g:343:5: (idcall= ID LPAREN ( listaArgs )? RPAREN SEMICOLON )
			// simple.g:343:7: idcall= ID LPAREN ( listaArgs )? RPAREN SEMICOLON
			{
			idcall=(Token)match(input,ID,FOLLOW_ID_in_methodCallStmt804); 
			match(input,LPAREN,FOLLOW_LPAREN_in_methodCallStmt806); 
			// simple.g:343:24: ( listaArgs )?
			int alt19=2;
			int LA19_0 = input.LA(1);
			if ( ((LA19_0 >= CFLOAT && LA19_0 <= CINT)||LA19_0==ID||LA19_0==LPAREN) ) {
				alt19=1;
			}
			switch (alt19) {
				case 1 :
					// simple.g:343:24: listaArgs
					{
					pushFollow(FOLLOW_listaArgs_in_methodCallStmt808);
					listaArgs();
					state._fsp--;

					}
					break;

			}

			match(input,RPAREN,FOLLOW_RPAREN_in_methodCallStmt811); 
			match(input,SEMICOLON,FOLLOW_SEMICOLON_in_methodCallStmt813); 

			          registrarLlamadaMetodo((idcall!=null?idcall.getText():null));
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "methodCallStmt"



	// $ANTLR start "accessModif"
	// simple.g:352:1: accessModif : ( PUBLIC | PRIVATE | PROTECTED );
	public final void accessModif() throws RecognitionException {
		try {
			// simple.g:352:13: ( PUBLIC | PRIVATE | PROTECTED )
			// simple.g:
			{
			if ( (input.LA(1) >= PRIVATE && input.LA(1) <= PUBLIC) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "accessModif"


	public static class type_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "type"
	// simple.g:353:1: type : ( INT | DOUBLE | BOOLEAN );
	public final simpleParser.type_return type() throws RecognitionException {
		simpleParser.type_return retval = new simpleParser.type_return();
		retval.start = input.LT(1);

		try {
			// simple.g:353:13: ( INT | DOUBLE | BOOLEAN )
			// simple.g:
			{
			if ( input.LA(1)==BOOLEAN||input.LA(1)==DOUBLE||input.LA(1)==INT ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "type"

	// Delegated rules



	public static final BitSet FOLLOW_theClass_in_program31 = new BitSet(new long[]{0x0000000000070002L});
	public static final BitSet FOLLOW_accessModif_in_theClass54 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLASS_in_theClass56 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_ID_in_theClass60 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_OCURLYB_in_theClass70 = new BitSet(new long[]{0x0000000000072830L});
	public static final BitSet FOLLOW_member_in_theClass72 = new BitSet(new long[]{0x0000000000072830L});
	public static final BitSet FOLLOW_CCURLYB_in_theClass75 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_method_in_member101 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_property_in_member109 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_accessModif_in_property128 = new BitSet(new long[]{0x0000000000002810L});
	public static final BitSet FOLLOW_type_in_property134 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_ID_in_property138 = new BitSet(new long[]{0x0000000008200000L});
	public static final BitSet FOLLOW_27_in_property149 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_ID_in_property153 = new BitSet(new long[]{0x0000000008200000L});
	public static final BitSet FOLLOW_SEMICOLON_in_property165 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_accessModif_in_method186 = new BitSet(new long[]{0x0000000000002810L});
	public static final BitSet FOLLOW_type_in_method192 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_ID_in_method196 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_LPAREN_in_method214 = new BitSet(new long[]{0x0000000000102810L});
	public static final BitSet FOLLOW_decla_args_in_method216 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_RPAREN_in_method219 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_OCURLYB_in_method235 = new BitSet(new long[]{0x0000000000083A30L});
	public static final BitSet FOLLOW_sentences_in_method237 = new BitSet(new long[]{0x0000000000083A30L});
	public static final BitSet FOLLOW_CCURLYB_in_method240 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_type_in_decla_args268 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_ID_in_decla_args272 = new BitSet(new long[]{0x0000000008000002L});
	public static final BitSet FOLLOW_27_in_decla_args283 = new BitSet(new long[]{0x0000000000002810L});
	public static final BitSet FOLLOW_type_in_decla_args287 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_ID_in_decla_args291 = new BitSet(new long[]{0x0000000008000002L});
	public static final BitSet FOLLOW_private_declar_in_sentences315 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assigment_in_sentences323 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_doWhileStmt_in_sentences331 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_returnStmt_in_sentences339 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_methodCallStmt_in_sentences347 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_type_in_private_declar368 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_ID_in_private_declar372 = new BitSet(new long[]{0x0000000008200000L});
	public static final BitSet FOLLOW_27_in_private_declar383 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_ID_in_private_declar387 = new BitSet(new long[]{0x0000000008200000L});
	public static final BitSet FOLLOW_SEMICOLON_in_private_declar399 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_assigment419 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_32_in_assigment421 = new BitSet(new long[]{0x00000000000050C0L});
	public static final BitSet FOLLOW_expr_in_assigment425 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_SEMICOLON_in_assigment427 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_multExpr_in_expr462 = new BitSet(new long[]{0x0000000014000002L});
	public static final BitSet FOLLOW_set_in_expr476 = new BitSet(new long[]{0x00000000000050C0L});
	public static final BitSet FOLLOW_multExpr_in_expr486 = new BitSet(new long[]{0x0000000014000002L});
	public static final BitSet FOLLOW_RETURN_in_returnStmt507 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_SEMICOLON_in_returnStmt509 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_RETURN_in_returnStmt517 = new BitSet(new long[]{0x00000000000050C0L});
	public static final BitSet FOLLOW_expr_in_returnStmt519 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_SEMICOLON_in_returnStmt521 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_atom_in_multExpr546 = new BitSet(new long[]{0x0000000022000002L});
	public static final BitSet FOLLOW_set_in_multExpr560 = new BitSet(new long[]{0x00000000000050C0L});
	public static final BitSet FOLLOW_atom_in_multExpr570 = new BitSet(new long[]{0x0000000022000002L});
	public static final BitSet FOLLOW_CINT_in_atom596 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CFLOAT_in_atom606 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_atom618 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_LPAREN_in_atom620 = new BitSet(new long[]{0x00000000001050C0L});
	public static final BitSet FOLLOW_listaArgs_in_atom622 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_RPAREN_in_atom625 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_atom637 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_atom647 = new BitSet(new long[]{0x00000000000050C0L});
	public static final BitSet FOLLOW_expr_in_atom651 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_RPAREN_in_atom653 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_listaArgs675 = new BitSet(new long[]{0x0000000008000002L});
	public static final BitSet FOLLOW_27_in_listaArgs678 = new BitSet(new long[]{0x00000000000050C0L});
	public static final BitSet FOLLOW_expr_in_listaArgs682 = new BitSet(new long[]{0x0000000008000002L});
	public static final BitSet FOLLOW_expr_in_relExpr710 = new BitSet(new long[]{0x0000000EC1000000L});
	public static final BitSet FOLLOW_set_in_relExpr714 = new BitSet(new long[]{0x00000000000050C0L});
	public static final BitSet FOLLOW_expr_in_relExpr740 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DO_in_doWhileStmt762 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_OCURLYB_in_doWhileStmt764 = new BitSet(new long[]{0x0000000000083A30L});
	public static final BitSet FOLLOW_sentences_in_doWhileStmt766 = new BitSet(new long[]{0x0000000000083A30L});
	public static final BitSet FOLLOW_CCURLYB_in_doWhileStmt769 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_WHILE_in_doWhileStmt771 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_LPAREN_in_doWhileStmt773 = new BitSet(new long[]{0x00000000000050C0L});
	public static final BitSet FOLLOW_relExpr_in_doWhileStmt777 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_RPAREN_in_doWhileStmt779 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_SEMICOLON_in_doWhileStmt781 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_methodCallStmt804 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_LPAREN_in_methodCallStmt806 = new BitSet(new long[]{0x00000000001050C0L});
	public static final BitSet FOLLOW_listaArgs_in_methodCallStmt808 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_RPAREN_in_methodCallStmt811 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_SEMICOLON_in_methodCallStmt813 = new BitSet(new long[]{0x0000000000000002L});
}
