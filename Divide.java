import static utils.Utilities.writeFile;

import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;
/**
 * Performs a simple division problem with integers, double, or long.
 * 
 * @authors Drake Hovsepian, Spencer Childers
 *
 */
public class Divide{

    public static void main(String[] args){

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"DivideNumbers", null, "java/lang/Object",null);
        
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
            
            //Integer Divide
            mv.visitIntInsn(Opcodes.BIPUSH, 10);//Creating integer 10
            mv.visitVarInsn(Opcodes.ISTORE, 1);//Storing 10 in memory location 1
            mv.visitInsn(Opcodes.ICONST_5);//Creating integer 5
            mv.visitVarInsn(Opcodes.ISTORE, 2);//Storing 2 in memory location 2
            mv.visitVarInsn(Opcodes.ILOAD, 1);//Pulling integer 10
            mv.visitVarInsn(Opcodes.ILOAD, 2);//Pulling integer 5
            mv.visitInsn(Opcodes.IDIV);//Dividing 10 by 5
            mv.visitVarInsn(Opcodes.ISTORE, 3);//Storing answer in memory location 3
            
            //Double Division
            mv.visitLdcInsn(new Double("20.5"));//Creating double 20.5
            mv.visitVarInsn(Opcodes.DSTORE, 4);//Storing 20.5 in memory location 4
            mv.visitLdcInsn(new Double("10.25"));//Creating double 10.25
            mv.visitVarInsn(Opcodes.DSTORE, 6);//storing 10.25 in memory location 6
            mv.visitVarInsn(Opcodes.DLOAD, 4);//Pulling item at memory location 4. Number 20.5
            mv.visitVarInsn(Opcodes.DLOAD, 6);//Pulling item at memory location 6. Number 10.25
            mv.visitInsn(Opcodes.DDIV);//Dividing 20.5 by 10.25
            mv.visitVarInsn(Opcodes.DSTORE, 8);//Storing answer at memory location 8
            
            //Long Division
            mv.visitLdcInsn(new Long(12L));//Creating long number 12
            mv.visitVarInsn(Opcodes.LSTORE, 10);//Storing long 12 in memory location 10
            mv.visitLdcInsn(new Long(5L));//Creating long number 5
            mv.visitVarInsn(Opcodes.LSTORE, 12);//Storing long 5 in memory location 12
            mv.visitVarInsn(Opcodes.LLOAD, 10);//Pulling memory location 10. Number 12
            mv.visitVarInsn(Opcodes.LLOAD, 12);//Loading item at memory location 12. Number 5.
            mv.visitInsn(Opcodes.LDIV);//Dividing item at memory location 10 by item at memory location 12. Dividing 12/5
            mv.visitVarInsn(Opcodes.LSTORE, 14);//Storing answer in memory location 14.
            
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(4, 16);
            mv.visitEnd();

        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"DivideNumbers.class");
        
        System.out.println("Done!");
    }//end main
}//end class