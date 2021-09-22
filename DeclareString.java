
import static utils.Utilities.writeFile;

import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;
/**
 * Declares a single string.
 * 
 * @authors Drake Hovsepian, Spencer Childers
 *
 */
public class DeclareString{

    public static void main(String[] args){

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"DeclareString", null, "java/lang/Object",null);
        
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
            mv.visitTypeInsn(Opcodes.NEW, "java/lang/String");//Readies the string method to be used
            mv.visitInsn(Opcodes.DUP);//Duplicates top stack position
            mv.visitLdcInsn("Strength");//String contents declared
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/String", "<init>", "(Ljava/lang/String;)V", false);//Invokes the string builder
            mv.visitVarInsn(Opcodes.ASTORE, 1);//Stores the string in memory position 1
            
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(3, 2);
            mv.visitEnd();

        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"DeclareString.class");
        
        System.out.println("Done!");
    }//end main
}//end class
