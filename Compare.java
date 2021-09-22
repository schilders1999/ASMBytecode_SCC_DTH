import static utils.Utilities.writeFile;

import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;
/**
 * Compares two integer, long, or double values to determine which of them is greater.
 * 
 * @authors Drake Hovsepian, Spencer Childers
 *
 */
public class Compare{

    public static void main(String[] args){

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"CompareText", null, "java/lang/Object",null);
        
        {
			MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(1,1);
			mv.visitEnd();
		}

        {
            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            
            //int section
            mv.visitVarInsn(Opcodes.BIPUSH, 7);//pushes int value 7
            mv.visitVarInsn(Opcodes.ISTORE, 1);//stores int in location 1
            mv.visitIntInsn(Opcodes.BIPUSH, 6);//pushes int value 6
            mv.visitVarInsn(Opcodes.ISTORE, 2);//stores int in location 2 
            mv.visitVarInsn(Opcodes.ILOAD, 1); //loads variable in location 1
            mv.visitVarInsn(Opcodes.ILOAD, 2); //loads variable in location 2
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Math", "max", "(II)I", false);//invokes a class static method, Math.max()
            mv.visitVarInsn(Opcodes.ISTORE, 3);//stores int value from Math.max() in location 3
            
            //long section
            mv.visitLdcInsn(new Long(14L)); 
            mv.visitVarInsn(Opcodes.LSTORE, 4);
            mv.visitLdcInsn(new Long(12L));
            mv.visitVarInsn(Opcodes.LSTORE, 6);
            mv.visitVarInsn(Opcodes.LLOAD, 4);
            mv.visitVarInsn(Opcodes.LLOAD, 6);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Math", "max", "(II)I", false);
            mv.visitVarInsn(Opcodes.LSTORE, 8);
            
            //double section
            mv.visitLdcInsn((Double)46.67);
            mv.visitVarInsn(Opcodes.DSTORE, 10);
            mv.visitLdcInsn((Double) 46.55);
            mv.visitVarInsn(Opcodes.DSTORE, 12);
            mv.visitVarInsn(Opcodes.DLOAD, 10);
            mv.visitVarInsn(Opcodes.DLOAD, 12);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Math", "max", "(II)I", false);
            mv.visitVarInsn(Opcodes.DSTORE, 14);
            
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(2, 5);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"CompareText.class");
        
        System.out.println("Done!");
    }//end main
}//end class
