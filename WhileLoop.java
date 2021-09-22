import static utils.Utilities.writeFile;

import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;

/**
 * Creates a simple while loop that terminates once a counter reaches 5.
 * 
 * @authors Drake Hovsepian, Spencer Childers
 *
 */
public class WhileLoop{

    public static void main(String[] args){

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"WhileLoopFile", null, "java/lang/Object",null);
        
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
            mv.visitInsn(Opcodes.ICONST_0);//Creating an integer 0.
            mv.visitVarInsn(Opcodes.ISTORE, 1);//Storing integer 0 at memory location 1.
            Label l0 = new Label();//Creating label l0.
            mv.visitLabel(l0);//Call position for label l0.
            mv.visitFrame(Opcodes.F_APPEND,1, new Object[] {Opcodes.INTEGER}, 0, null);
            mv.visitVarInsn(Opcodes.ILOAD, 1);//Call item at memory location 1. 
            mv.visitInsn(Opcodes.ICONST_5);//Creating integer 5
            Label l1 = new Label();//Creating label l1.
            mv.visitJumpInsn(Opcodes.IF_ICMPGE, l1);//While loop parameter. Repeats while ILOAD, 1 is less than 5. 
													//If value loaded from location 1 is greater than 5, go to position l1.
            mv.visitIincInsn(1, 1);//Increments int at memory location 1 by +1.
            mv.visitJumpInsn(Opcodes.GOTO, l0);//Command to jump to visitLabel(l0).
            mv.visitLabel(l1);//Call position for label l1.
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"WhileLoopFile.class");
        
        System.out.println("Done!");
    }//end main
}//end class
