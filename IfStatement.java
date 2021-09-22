import static utils.Utilities.writeFile;

import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;
/**
 * IfStatement contains a simple if else statement.
 * 
 * @authors Drake Hovsepian, Spencer Childers
 * 
 */
public class IfStatement{

    public static void main(String[] args){

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"IfText", null, "java/lang/Object",null);
        
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
            mv.visitIntInsn(Opcodes.BIPUSH, 10);//Creating int 10
            mv.visitVarInsn(Opcodes.ISTORE, 1);//Storing int in memory position 1
            mv.visitVarInsn(Opcodes.ILOAD, 1);//Loading item in memory location 1. Int 10.
            mv.visitInsn(Opcodes.ICONST_5);//Creating int 5
            Label l0 = new Label();//Creating label l0.
            mv.visitJumpInsn(Opcodes.IF_ICMPLE, l0);//if value stored at location 1 is less than or equal to 5, go to l0.
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//Prepares the print method
            mv.visitLdcInsn("hoo");//The content ot be printed																		
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);//Invokes the printer to print 
            Label l1 = new Label();//Creating label l1.
            mv.visitJumpInsn(Opcodes.GOTO, l1);//Jump to call position l1.
            mv.visitLabel(l0);//Call position for l0.
            mv.visitFrame(Opcodes.F_APPEND,1, new Object[] {Opcodes.INTEGER}, 0, null);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//Prepares the print method
            mv.visitLdcInsn("haa");//The content to be printed
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);//Invokes the printer to print
            mv.visitLabel(l1);///Call position for l1
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"IfText.class");
        
        System.out.println("Done!");
    }//end main
}//end class