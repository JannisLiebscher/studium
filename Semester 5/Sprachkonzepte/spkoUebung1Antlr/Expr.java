// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class Expr extends Lexer {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TIME=1, WS=2;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"TIME", "MIDDAY", "MIDDAY_SHORT", "HOUR", "MINUTE", "MERIDIEM", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TIME", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public Expr(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Expr.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0002\\\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0003\u0000\u0018\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001*\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"4\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002B\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u0003G\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005T\b\u0005\u0001\u0006\u0004\u0006W\b\u0006\u000b"+
		"\u0006\f\u0006X\u0001\u0006\u0001\u0006\u0000\u0000\u0007\u0001\u0001"+
		"\u0003\u0000\u0005\u0000\u0007\u0000\t\u0000\u000b\u0000\r\u0002\u0001"+
		"\u0000\u0004\u0001\u000001\u0001\u000009\u0001\u000005\u0003\u0000\t\n"+
		"\r\r  ^\u0000\u0001\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000"+
		"\u0000\u0001\u0017\u0001\u0000\u0000\u0000\u00033\u0001\u0000\u0000\u0000"+
		"\u0005A\u0001\u0000\u0000\u0000\u0007F\u0001\u0000\u0000\u0000\tH\u0001"+
		"\u0000\u0000\u0000\u000bS\u0001\u0000\u0000\u0000\rV\u0001\u0000\u0000"+
		"\u0000\u000f\u0018\u0003\u0005\u0002\u0000\u0010\u0018\u0003\u0003\u0001"+
		"\u0000\u0011\u0012\u0003\u0007\u0003\u0000\u0012\u0013\u0005:\u0000\u0000"+
		"\u0013\u0014\u0003\t\u0004\u0000\u0014\u0015\u0005 \u0000\u0000\u0015"+
		"\u0016\u0003\u000b\u0005\u0000\u0016\u0018\u0001\u0000\u0000\u0000\u0017"+
		"\u000f\u0001\u0000\u0000\u0000\u0017\u0010\u0001\u0000\u0000\u0000\u0017"+
		"\u0011\u0001\u0000\u0000\u0000\u0018\u0002\u0001\u0000\u0000\u0000\u0019"+
		"\u001a\u00051\u0000\u0000\u001a\u001b\u00052\u0000\u0000\u001b\u001c\u0005"+
		" \u0000\u0000\u001c)\u0001\u0000\u0000\u0000\u001d\u001e\u0005n\u0000"+
		"\u0000\u001e\u001f\u0005o\u0000\u0000\u001f \u0005o\u0000\u0000 *\u0005"+
		"n\u0000\u0000!\"\u0005m\u0000\u0000\"#\u0005i\u0000\u0000#$\u0005d\u0000"+
		"\u0000$%\u0005n\u0000\u0000%&\u0005i\u0000\u0000&\'\u0005g\u0000\u0000"+
		"\'(\u0005h\u0000\u0000(*\u0005t\u0000\u0000)\u001d\u0001\u0000\u0000\u0000"+
		")!\u0001\u0000\u0000\u0000*4\u0001\u0000\u0000\u0000+,\u00051\u0000\u0000"+
		",-\u00052\u0000\u0000-.\u0005:\u0000\u0000./\u00050\u0000\u0000/0\u0001"+
		"\u0000\u0000\u000001\u0007\u0000\u0000\u000012\u0005 \u0000\u000024\u0003"+
		"\u000b\u0005\u00003\u0019\u0001\u0000\u0000\u00003+\u0001\u0000\u0000"+
		"\u00004\u0004\u0001\u0000\u0000\u000056\u0005N\u0000\u000067\u0005o\u0000"+
		"\u000078\u0005o\u0000\u00008B\u0005n\u0000\u00009:\u0005M\u0000\u0000"+
		":;\u0005i\u0000\u0000;<\u0005d\u0000\u0000<=\u0005n\u0000\u0000=>\u0005"+
		"i\u0000\u0000>?\u0005g\u0000\u0000?@\u0005h\u0000\u0000@B\u0005t\u0000"+
		"\u0000A5\u0001\u0000\u0000\u0000A9\u0001\u0000\u0000\u0000B\u0006\u0001"+
		"\u0000\u0000\u0000CG\u0007\u0001\u0000\u0000DE\u00051\u0000\u0000EG\u0005"+
		"1\u0000\u0000FC\u0001\u0000\u0000\u0000FD\u0001\u0000\u0000\u0000G\b\u0001"+
		"\u0000\u0000\u0000HI\u0007\u0002\u0000\u0000IJ\u0007\u0001\u0000\u0000"+
		"J\n\u0001\u0000\u0000\u0000KL\u0005a\u0000\u0000LM\u0005.\u0000\u0000"+
		"MN\u0005m\u0000\u0000NT\u0005.\u0000\u0000OP\u0005p\u0000\u0000PQ\u0005"+
		".\u0000\u0000QR\u0005m\u0000\u0000RT\u0005.\u0000\u0000SK\u0001\u0000"+
		"\u0000\u0000SO\u0001\u0000\u0000\u0000T\f\u0001\u0000\u0000\u0000UW\u0007"+
		"\u0003\u0000\u0000VU\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000"+
		"XV\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000"+
		"\u0000Z[\u0006\u0006\u0000\u0000[\u000e\u0001\u0000\u0000\u0000\b\u0000"+
		"\u0017)3AFSX\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}