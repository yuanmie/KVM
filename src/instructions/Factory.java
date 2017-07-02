package instructions;


import instructions.base.Instruction;
import instructions.comparisons.*;
import instructions.constants.*;
import instructions.loads.*;
import instructions.references.*;
import instructions.reserved.InvokeNative;
import instructions.stores.*;
import instructions.stack.*;
import instructions.math.*;
import instructions.conversions.*;
import instructions.extended.*;
import instructions.control.*;
import tool.Tool;


public class Factory {
    static Instruction nop;
    static Instruction aconst_null;
    static Instruction iconst_m1;
    static Instruction iconst_0;
    static Instruction iconst_1;
    static Instruction iconst_2;
    static Instruction iconst_3;
    static Instruction iconst_4;
    static Instruction iconst_5;
    static Instruction lconst_0;
    static Instruction lconst_1;
    static Instruction fconst_0;
    static Instruction fconst_1;
    static Instruction fconst_2;
    static Instruction dconst_0;
    static Instruction dconst_1;
    static Instruction iload_0;
    static Instruction iload_1;
    static Instruction iload_2;
    static Instruction iload_3;
    static Instruction lload_0;
    static Instruction lload_1;
    static Instruction lload_2;
    static Instruction lload_3;
    static Instruction fload_0;
    static Instruction fload_1;
    static Instruction fload_2;
    static Instruction fload_3;
    static Instruction dload_0;
    static Instruction dload_1;
    static Instruction dload_2;
    static Instruction dload_3;
    static Instruction aload_0;
    static Instruction aload_1;
    static Instruction aload_2;
    static Instruction aload_3;

    static Instruction istore_0;
    static Instruction istore_1;
    static Instruction istore_2;
    static Instruction istore_3;
    static Instruction lstore_0;
    static Instruction lstore_1;
    static Instruction lstore_2;
    static Instruction lstore_3;
    static Instruction fstore_0;
    static Instruction fstore_1;
    static Instruction fstore_2;
    static Instruction fstore_3;
    static Instruction dstore_0;
    static Instruction dstore_1;
    static Instruction dstore_2;
    static Instruction dstore_3;
    static Instruction astore_0;
    static Instruction astore_1;
    static Instruction astore_2;
    static Instruction astore_3;

    static Instruction pop;
    static Instruction pop2;
    static Instruction dup;
    static Instruction dup_x1;
    static Instruction dup_x2;
    static Instruction dup2;
    static Instruction dup2_x1;
    static Instruction dup2_x2;
    static Instruction swap;
    static Instruction iadd;
    static Instruction ladd;
    static Instruction fadd;
    static Instruction dadd;
    static Instruction isub;
    static Instruction lsub;
    static Instruction fsub;
    static Instruction dsub;
    static Instruction imul;
    static Instruction lmul;
    static Instruction fmul;
    static Instruction dmul;
    static Instruction idiv;
    static Instruction ldiv;
    static Instruction fdiv;
    static Instruction ddiv;
    static Instruction irem;
    static Instruction lrem;
    static Instruction frem;
    static Instruction drem;
    static Instruction ineg;
    static Instruction lneg;
    static Instruction fneg;
    static Instruction dneg;
    static Instruction ishl;
    static Instruction lshl;
    static Instruction ishr;
    static Instruction lshr;
    static Instruction iushr;
    static Instruction lushr;
    static Instruction iand;
    static Instruction land;
    static Instruction ior;
    static Instruction lor;
    static Instruction ixor;
    static Instruction lxor;
    static Instruction i2l;
    static Instruction i2f;
    static Instruction i2d;
    static Instruction l2i;
    static Instruction l2f;
    static Instruction l2d;
    static Instruction f2i;
    static Instruction f2l;
    static Instruction f2d;
    static Instruction d2i;
    static Instruction d2l;
    static Instruction d2f;
    static Instruction i2b;
    static Instruction i2c;
    static Instruction i2s;
    static Instruction lcmp;
    static Instruction fcmpl;
    static Instruction fcmpg;
    static Instruction dcmpl;
    static Instruction dcmpg;


    static final LRETURN lreturn;

    static final IRETURN ireturn;
    static final RETURN _return;
    private static Instruction arraylength;
    private static Instruction iaload;
    private static Instruction laload;
    private static Instruction faload;
    private static Instruction daload;
    private static Instruction aaload;
    private static Instruction baload;
    private static Instruction caload;
    private static Instruction saload;
    private static Instruction iastore;
    private static Instruction lastore;
    private static Instruction fastore;
    private static Instruction dastore;
    private static Instruction aastore;
    private static Instruction bastore;
    private static Instruction castore;
    private static Instruction sastore;
    private static Instruction freturn;
    private static Instruction dreturn;
    private static Instruction areturn;
    private static Instruction invokeNative;
    private static Instruction athrow;


    static {

        athrow = new ATHROW();
        iaload = new IALOAD();
        laload = new LALOAD();
        faload = new FALOAD();
        daload = new DALOAD();
        aaload = new AALOAD();
        baload = new BALOAD();
        caload = new CALOAD();
        saload = new SALOAD();

        iastore = new IASTORE();
        lastore = new LASTORE();
        fastore = new FASTORE();
        dastore = new DASTORE();
        aastore = new AASTORE();
        bastore = new BASTORE();
        castore = new CASTORE();
        sastore = new SASTORE();

        freturn = new FRETURN();
        dreturn = new DRETURN();
        areturn = new ARETURN();

        invokeNative = new InvokeNative();
         nop = new NOP();
         aconst_null = new ACONST_NULL();
         iconst_m1 = new ICONST_M1();
         iconst_0 = new ICONST_0();
         iconst_1 = new ICONST_1();
         iconst_2 = new ICONST_2();
         iconst_3 = new ICONST_3();
         iconst_4 = new ICONST_4();
         iconst_5 = new ICONST_5();
         lconst_0 = new LCONST_0();
         lconst_1 = new LCONST_1();
         fconst_0 = new FCONST_0();
         fconst_1 = new FCONST_1();
         fconst_2 = new FCONST_2();
         dconst_0 = new DCONST_0();
         dconst_1 = new DCONST_1();
         iload_0 = new ILOAD_0();
         iload_1 = new ILOAD_1();
         iload_2 = new ILOAD_2();
         iload_3 = new ILOAD_3();
         lload_0 = new LLOAD_0();
         lload_1 = new LLOAD_1();
         lload_2 = new LLOAD_2();
         lload_3 = new LLOAD_3();
         fload_0 = new FLOAD_0();
         fload_1 = new FLOAD_1();
         fload_2 = new FLOAD_2();
         fload_3 = new FLOAD_3();
         dload_0 = new DLOAD_0();
         dload_1 = new DLOAD_1();
         dload_2 = new DLOAD_2();
         dload_3 = new DLOAD_3();
         aload_0 = new ALOAD_0();
         aload_1 = new ALOAD_1();
         aload_2 = new ALOAD_2();
         aload_3 = new ALOAD_3();

         istore_0 = new ISTORE_0();
         istore_1 = new ISTORE_1();
         istore_2 = new ISTORE_2();
         istore_3 = new ISTORE_3();
         lstore_0 = new LSTORE_0();
         lstore_1 = new LSTORE_1();
         lstore_2 = new LSTORE_2();
         lstore_3 = new LSTORE_3();
         fstore_0 = new FSTORE_0();
         fstore_1 = new FSTORE_1();
         fstore_2 = new FSTORE_2();
         fstore_3 = new FSTORE_3();
         dstore_0 = new DSTORE_0();
         dstore_1 = new DSTORE_1();
         dstore_2 = new DSTORE_2();
         dstore_3 = new DSTORE_3();
         astore_0 = new ASTORE_0();
         astore_1 = new ASTORE_1();
         astore_2 = new ASTORE_2();
         astore_3 = new ASTORE_3();

         pop = new POP();
         pop2 = new POP2();
         dup = new DUP();
         dup_x1 = new DUP_X1();
         dup_x2 = new DUP_X2();
         dup2 = new DUP2();
         dup2_x1 = new DUP2_X1();
         dup2_x2 = new DUP2_X2();
         swap = new SWAP();
         iadd = new IADD();
         ladd = new LADD();
         fadd = new FADD();
         dadd = new DADD();
         isub = new ISUB();
         lsub = new LSUB();
         fsub = new FSUB();
         dsub = new DSUB();
         imul = new IMUL();
         lmul = new LMUL();
         fmul = new FMUL();
         dmul = new DMUL();
         idiv = new IDIV();
         ldiv = new LDIV();
         fdiv = new FDIV();
         ddiv = new DDIV();
         irem = new IREM();
         lrem = new LREM();
         frem = new FREM();
         drem = new DREM();
         ineg = new INEG();
         lneg = new LNEG();
         fneg = new FNEG();
         dneg = new DNEG();
         ishl = new ISHL();
         lshl = new LSHL();
         ishr = new ISHR();
         lshr = new LSHR();
         iushr = new IUSHR();
         lushr = new LUSHR();
         iand = new IAND();
         land = new LAND();
         ior = new IOR();
         lor = new LOR();
         ixor = new IXOR();
         lxor = new LXOR();
         i2l = new I2L();
         i2f = new I2F();
         i2d = new I2D();
         l2i = new L2I();
         l2f = new L2F();
         l2d = new L2D();
         f2i = new F2I();
         f2l = new F2L();
         f2d = new F2D();
         d2i = new D2I();
         d2l = new D2L();
         d2f = new D2F();
         i2b = new I2B();
         i2c = new I2C();
         i2s = new I2S();
         lcmp = new LCMP();
         fcmpl = new FCMPL();
         fcmpg = new FCMPG();
         dcmpl = new DCMPL();
         dcmpg = new DCMPG();

         lreturn = new LRETURN();
         ireturn = new IRETURN();
         _return = new RETURN();
         arraylength = new Array_length();

    }


    public static Instruction newInstruction(int opcode) {
        switch (opcode) {
            case 0x00:
                return nop;
            case 0x01:
                return aconst_null;
            case 0x02:
                return iconst_m1;
            case 0x03:
                return iconst_0;
            case 0x04:
                return iconst_1;
            case 0x05:
                return iconst_2;
            case 0x06:
                return iconst_3;
            case 0x07:
                return iconst_4;
            case 0x08:
                return iconst_5;
            case 0x09:
                return lconst_0;
            case 0x0a:
                return lconst_1;
            case 0x0b:
                return fconst_0;
            case 0x0c:
                return fconst_1;
            case 0x0d:
                return fconst_2;
            case 0x0e:
                return dconst_0;
            case 0x0f:
                return dconst_1;
            case 0x10:
                return new BIPUSH();
            case 0x11:
                return new SIPUSH();
             case 0x12:
             	return new LDC();
             case 0x13:
             	return new LDCW();
             case 0x14:
             	return new LDC2W();
            case 0x15:
                return new ILOAD();
            case 0x16:
                return new LLOAD();
            case 0x17:
                return new FLOAD();
            case 0x18:
                return new DLOAD();
            case 0x19:
                return new ALOAD();
            case 0x1a:
                return iload_0;
            case 0x1b:
                return iload_1;
            case 0x1c:
                return iload_2;
            case 0x1d:
                return iload_3;
            case 0x1e:
                return lload_0;
            case 0x1f:
                return lload_1;
            case 0x20:
                return lload_2;
            case 0x21:
                return lload_3;
            case 0x22:
                return fload_0;
            case 0x23:
                return fload_1;
            case 0x24:
                return fload_2;
            case 0x25:
                return fload_3;
            case 0x26:
                return dload_0;
            case 0x27:
                return dload_1;
            case 0x28:
                return dload_2;
            case 0x29:
                return dload_3;
            case 0x2a:
                return aload_0;
            case 0x2b:
                return aload_1;
            case 0x2c:
                return aload_2;
            case 0x2d:
                return aload_3;
             case 0x2e:
             	return iaload;
             case 0x2f:
             	return laload;
             case 0x30:
             	return faload;
             case 0x31:
             	return daload;
             case 0x32:
             	return aaload;
             case 0x33:
             	return baload;
             case 0x34:
             	return caload;
             case 0x35:
             	return saload;
            case 0x36:
                return new ISTORE();
            case 0x37:
                return new LSTORE();
            case 0x38:
                return new FSTORE();
            case 0x39:
                return new DSTORE();
            case 0x3a:
                return new ASTORE();
            case 0x3b:
                return istore_0;
            case 0x3c:
                return istore_1;
            case 0x3d:
                return istore_2;
            case 0x3e:
                return istore_3;
            case 0x3f:
                return lstore_0;
            case 0x40:
                return lstore_1;
            case 0x41:
                return lstore_2;
            case 0x42:
                return lstore_3;
            case 0x43:
                return fstore_0;
            case 0x44:
                return fstore_1;
            case 0x45:
                return fstore_2;
            case 0x46:
                return fstore_3;
            case 0x47:
                return dstore_0;
            case 0x48:
                return dstore_1;
            case 0x49:
                return dstore_2;
            case 0x4a:
                return dstore_3;
            case 0x4b:
                return astore_0;
            case 0x4c:
                return astore_1;
            case 0x4d:
                return astore_2;
            case 0x4e:
                return astore_3;
             case 0x4f:
             	return iastore;
             case 0x50:
             	return lastore;
             case 0x51:
             	return fastore;
             case 0x52:
             	return dastore;
             case 0x53:
             	return aastore;
             case 0x54:
             	return bastore;
             case 0x55:
             	return castore;
             case 0x56:
             	return sastore;
            case 0x57:
                return pop;
            case 0x58:
                return pop2;
            case 0x59:
                return dup;
            case 0x5a:
                return dup_x1;
            case 0x5b:
                return dup_x2;
            case 0x5c:
                return dup2;
            case 0x5d:
                return dup2_x1;
            case 0x5e:
                return dup2_x2;
            case 0x5f:
                return swap;
            case 0x60:
                return iadd;
            case 0x61:
                return ladd;
            case 0x62:
                return fadd;
            case 0x63:
                return dadd;
            case 0x64:
                return isub;
            case 0x65:
                return lsub;
            case 0x66:
                return fsub;
            case 0x67:
                return dsub;
            case 0x68:
                return imul;
            case 0x69:
                return lmul;
            case 0x6a:
                return fmul;
            case 0x6b:
                return dmul;
            case 0x6c:
                return idiv;
            case 0x6d:
                return ldiv;
            case 0x6e:
                return fdiv;
            case 0x6f:
                return ddiv;
            case 0x70:
                return irem;
            case 0x71:
                return lrem;
            case 0x72:
                return frem;
            case 0x73:
                return drem;
            case 0x74:
                return ineg;
            case 0x75:
                return lneg;
            case 0x76:
                return fneg;
            case 0x77:
                return dneg;
            case 0x78:
                return ishl;
            case 0x79:
                return lshl;
            case 0x7a:
                return ishr;
            case 0x7b:
                return lshr;
            case 0x7c:
                return iushr;
            case 0x7d:
                return lushr;
            case 0x7e:
                return iand;
            case 0x7f:
                return land;
            case 0x80:
                return ior;
            case 0x81:
                return lor;
            case 0x82:
                return ixor;
            case 0x83:
                return lxor;
            case 0x84:
                return new IINC();
            case 0x85:
                return i2l;
            case 0x86:
                return i2f;
            case 0x87:
                return i2d;
            case 0x88:
                return l2i;
            case 0x89:
                return l2f;
            case 0x8a:
                return l2d;
            case 0x8b:
                return f2i;
            case 0x8c:
                return f2l;
            case 0x8d:
                return f2d;
            case 0x8e:
                return d2i;
            case 0x8f:
                return d2l;
            case 0x90:
                return d2f;
            case 0x91:
                return i2b;
            case 0x92:
                return i2c;
            case 0x93:
                return i2s;
            case 0x94:
                return lcmp;
            case 0x95:
                return fcmpl;
            case 0x96:
                return fcmpg;
            case 0x97:
                return dcmpl;
            case 0x98:
                return dcmpg;
            case 0x99:
                return new IF_EQ();
            case 0x9a:
                return new IF_NE();
            case 0x9b:
                return new IF_LT();
            case 0x9c:
                return new IF_GE();
            case 0x9d:
                return new IF_GT();
            case 0x9e:
                return new IF_LE();
            case 0x9f:
                return new IF_ICMPEQ();
            case 0xa0:
                return new IF_ICMPNE();
            case 0xa1:
                return new IF_ICMPLT();
            case 0xa2:
                return new IF_ICMPGE();
            case 0xa3:
                return new IF_ICMPGT();
            case 0xa4:
                return new IF_ICMPLE();
            case 0xa5:
                return new IF_ACMPEQ();
            case 0xa6:
                return new IF_ACMPNE();
            case 0xa7:
                return new GOTO();
            // case 0xa8:
            // 	return new JSR()
            // case 0xa9:
            // 	return new RET()
            case 0xaa:
                return new TABLE_SWITCH();
            case 0xab:
                return new LOOKUP_SWITCH();
             case 0xac:
             	return ireturn;
             case 0xad:
             	return lreturn;
             case 0xae:
             	return freturn;
             case 0xaf:
             	return dreturn;
             case 0xb0:
             	return areturn;
             case 0xb1:
             	return _return;
            	case 0xb2:
            		return new GetStatic();
             case 0xb3:
             	return new PutStatic();
             case 0xb4:
             	return new GetField();
             case 0xb5:
             	return new PutField();
            	case 0xb6:
            		return new INVOKE_VIRTUAL();
             case 0xb7:
             	return new INVOKE_SPECIAL();
             case 0xb8:
             	return new InvokeStatic();
             case 0xb9:
             	return new INVOKE_INTERFACE();
//             case 0xba:
//             	return new INVOKE_DYNAMIC();
             case 0xbb:
             	return new JVMNEW();
             case 0xbc:
             	return new NEW_ARRAY();
             case 0xbd:
             	return new ANEW_ARRAY();
             case 0xbe:
             	return arraylength;
             case 0xbf:
             	return athrow;
             case 0xc0:
             	return new CheckCast();
             case 0xc1:
             	return new JVMInstanceof();
            // case 0xc2:
            // 	return monitorenter
            // case 0xc3:
            // 	return monitorexit
            case 0xc4:
                return new WIDE();
             case 0xc5:
             	return new MULTI_ANEW_ARRAY();
            case 0xc6:
                return new IFNULL();
            case 0xc7:
                return new IFNONNULL();
            case 0xc8:
                return new GOTO_W();
            // case 0xc9:
            // 	return new JSR_W()
            // case 0xca: breakpoint
            case 0xfe: return invokeNative;
            // case 0xff: impdep2
            default:
                Tool.panic(String.format("Unsupported opcode: 0x%x!", opcode));
        }
        return null;
    }
}
