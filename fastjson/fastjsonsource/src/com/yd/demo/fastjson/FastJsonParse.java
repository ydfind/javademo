package com.yd.demo.fastjson;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.JSONToken;
/**
 * 将
 *    "{\"respCd\":"0000\",\"msg\":\"查询成功\",\"result\":[{\"data1\":\"value1\"}]}"
 * 变为
 *    {"respCd":"0000","msg":"查询成功","result":[{"data1":"value1"}]}
 * @author Yudi
 *
 */
public class FastJsonParse {
    private static char EOI            = 0x1A;
    private char[] sbuf;
	int bp = 0;// text的位置
	int sp = 0;// sbuf的下一个位置
	char ch;
	protected final static int[] digits                = new int[(int) 'f' + 1];
    FastJsonParse(){
		bp = 0;// text的位置
		sp = 0;// sp的位置
		sbuf = new char[1];
    	
    }
	
	private char next(String text) {
	        int index = ++bp;
	        return ch = (index >= text.length() ? //
	                EOI //
	                : text.charAt(index));
	}
	
	private boolean isEOF(String text, char ch) {
        return bp == text.length() || ch == EOI && bp + 1 == text.length();
    }
	
	private void putChar(char ch) {
        if (sp == sbuf.length) {
            char[] newsbuf = new char[sbuf.length * 2];
            System.arraycopy(sbuf, 0, newsbuf, 0, sbuf.length);
            sbuf = newsbuf;
        }
        sbuf[sp++] = ch;
    }
	
	private void copyTo(String text, int offset, int count, char[] dest) {
        text.getChars(offset, offset + count, dest, 0);
    }
	
	public String scanString(String text) {
        int np = bp;
        boolean hasSpecial = false;
        char ch;
        for (;;) {
            ch = next(text);

            if (ch == '\"') {
                break;
            }

            if (ch == EOI) {
                if (!isEOF(text, ch)) {
                    putChar((char) EOI);
                    continue;
                }
                throw new JSONException("unclosed string : " + ch);
            }

            if (ch == '\\') {
                if (!hasSpecial) {
                    hasSpecial = true;

                    if (sp >= sbuf.length) {
                        int newCapcity = sbuf.length * 2;
                        if (sp > newCapcity) {
                            newCapcity = sp;
                        }
                        char[] newsbuf = new char[newCapcity];
                        System.arraycopy(sbuf, 0, newsbuf, 0, sbuf.length);
                        sbuf = newsbuf;
                    }

                    copyTo(text, np + 1, sp, sbuf);
                    // text.getChars(np + 1, np + 1 + sp, sbuf, 0);
                    // System.arraycopy(buf, np + 1, sbuf, 0, sp);
                }

                ch = next(text);

                switch (ch) {
                    case '0':
                        putChar('\0');
                        break;
                    case '1':
                        putChar('\1');
                        break;
                    case '2':
                        putChar('\2');
                        break;
                    case '3':
                        putChar('\3');
                        break;
                    case '4':
                        putChar('\4');
                        break;
                    case '5':
                        putChar('\5');
                        break;
                    case '6':
                        putChar('\6');
                        break;
                    case '7':
                        putChar('\7');
                        break;
                    case 'b': // 8
                        putChar('\b');
                        break;
                    case 't': // 9
                        putChar('\t');
                        break;
                    case 'n': // 10
                        putChar('\n');
                        break;
                    case 'v': // 11
                        putChar('\u000B');
                        break;
                    case 'f': // 12
                    case 'F':
                        putChar('\f');
                        break;
                    case 'r': // 13
                        putChar('\r');
                        break;
                    case '"': // 34
                        putChar('"');
                        break;
                    case '\'': // 39
                        putChar('\'');
                        break;
                    case '/': // 47
                        putChar('/');
                        break;
                    case '\\': // 92
                        putChar('\\');
                        break;
                    case 'x':// DRD NOTE: 这是什么情况
                    	System.out.println("0当前ch = " + ch + "; sbuf = " + sbuf);
                        char x1 = ch = next(text);
                        char x2 = ch = next(text);

                        int x_val = digits[x1] * 16 + digits[x2];
                        char x_char = (char) x_val;
                        putChar(x_char);
                    	System.out.println("1当前ch = " + ch + "; sbuf = " + sbuf);
                        break;
                    case 'u':
                    	System.out.println("10当前ch = " + ch + "; sbuf = " + sbuf);
                        char u1 = ch = next(text);
                        char u2 = ch = next(text);
                        char u3 = ch = next(text);
                        char u4 = ch = next(text);
                        int val = Integer.parseInt(new String(new char[] { u1, u2, u3, u4 }), 16);
                        putChar((char) val);
                    	System.out.println("11当前ch = " + ch + "; sbuf = " + sbuf);
                        break;
                    default:
                        this.ch = ch;
                        throw new JSONException("unclosed string : " + ch);
                }
                continue;
            }

            if (!hasSpecial) {
                sp++;
                continue;
            }

            if (sp == sbuf.length) {
                putChar(ch);
            } else {
                sbuf[sp++] = ch;
            }
        }

//        token = JSONToken.LITERAL_STRING;
        this.ch = next(text);
        
        
        if (!hasSpecial) {
//            int offset = np + 1;
//            if (offset < 0) {
//                throw new IllegalStateException();
//            }
//            if (offset > buf.length - sp) {
//                throw new IllegalStateException();
//            }
//            return new String(buf, offset, sp);
//            // return text.substring(np + 1, np + 1 + sp);
        	return text;
        } else {
            return new String(sbuf, 0, sp);
        }
    }

}
