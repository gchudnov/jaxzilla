/* JsonDriver.java */
/* Generated By:JavaCC: Do not edit this line. JsonDriver.java */
package com.github.gchudnov.jaxzilla.internal;

public class JsonDriver extends JsonDriverBase implements JsonDriverConstants {

// ENTRY-POINT
  final public void any() throws ParseException {
startDocument();
    Value();
    jj_consume_token(0);
endDocument();
}

// STRUCTURES
  final public 
void Value() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case NUMBER_INTEGER:
    case NUMBER_DECIMAL:
    case TRUE:
    case FALSE:
    case NULL:
    case STRING:{
      SimpleValue();
      break;
      }
    case LBRACE:{
      Object();
      break;
      }
    case LBRACKET:{
      Array();
      break;
      }
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  final public void Object() throws ParseException {Token t;
    jj_consume_token(LBRACE);
startObject();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case STRING:{
      Members();
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    jj_consume_token(RBRACE);
endObject();
}

  final public void Members() throws ParseException {
    Member();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COMMA:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_1;
      }
      jj_consume_token(COMMA);
      Member();
    }
}

  final public void Member() throws ParseException {Token t;
startKeyValue();
    t = jj_consume_token(STRING);
key(t.image);
    jj_consume_token(COLON);
    Value();
endKeyValue();
}

  final public void Array() throws ParseException {
    jj_consume_token(LBRACKET);
startArray();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LBRACE:
    case LBRACKET:
    case NUMBER_INTEGER:
    case NUMBER_DECIMAL:
    case TRUE:
    case FALSE:
    case NULL:
    case STRING:{
      Elements();
      break;
      }
    default:
      jj_la1[3] = jj_gen;
      ;
    }
    jj_consume_token(RBRACKET);
endArray();
}

  final public void Elements() throws ParseException {
    ArrayValue();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COMMA:{
        ;
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        break label_2;
      }
      jj_consume_token(COMMA);
      ArrayValue();
    }
}

  final public void ArrayValue() throws ParseException {
startElement();
    Value();
endElement();
}

  final public void SimpleValue() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case STRING:{
      String();
      break;
      }
    case NUMBER_INTEGER:
    case NUMBER_DECIMAL:{
      Number();
      break;
      }
    case TRUE:{
      True();
      break;
      }
    case FALSE:{
      False();
      break;
      }
    case NULL:{
      Null();
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  final public void Number() throws ParseException {Token t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case NUMBER_INTEGER:{
      t = jj_consume_token(NUMBER_INTEGER);
valueInteger(t.image);
      break;
      }
    case NUMBER_DECIMAL:{
      t = jj_consume_token(NUMBER_DECIMAL);
valueDecimal(t.image);
      break;
      }
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  final public void String() throws ParseException {Token t;
    t = jj_consume_token(STRING);
valueString(t.image);
}

  final public void True() throws ParseException {
    jj_consume_token(TRUE);
valueBoolean(true);
}

  final public void False() throws ParseException {
    jj_consume_token(FALSE);
valueBoolean(false);
}

  final public void Null() throws ParseException {
    jj_consume_token(NULL);
valueNull();
}

  /** Generated Token Manager. */
  public JsonDriverTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[7];
  static private int[] jj_la1_0;
  static {
	   jj_la1_init_0();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x87c090,0x800000,0x8,0x87c090,0x8,0x87c000,0xc000,};
	}

  /** Constructor with InputStream. */
  public JsonDriver(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public JsonDriver(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new JsonDriverTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 7; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 7; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public JsonDriver(java.io.Reader stream) {
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new JsonDriverTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 7; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new JsonDriverTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 7; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public JsonDriver(JsonDriverTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 7; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(JsonDriverTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 7; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[24];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 7; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 24; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  private int trace_indent = 0;
  private boolean trace_enabled;

/** Trace enabled. */
  final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

                                                 }
