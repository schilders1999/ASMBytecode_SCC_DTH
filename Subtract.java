import static utils.Utilities.writeFile;
import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;

/**
 * Subtracts two integers, doubles, or long numbers.
 * 
 * @authors Drake Hovsepian, Spencer Childers
 *
 */
public class Subtract{

    public static void main(String[] args){

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"Subtraction", null, "java/lang/Object",null);
        
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
            mv.visitVarInsn(Opcodes.BIPUSH, 66);//pushes a number of bytes which are translated as an int value, necessary to push an int value higher then 5
            mv.visitVarInsn(Opcodes.ISTORE, 1);//stores available int value as a local variable
            mv.visitInsn(Opcodes.ICONST_3); //creates an int value as a constant, only usable between -1 and 5
            mv.visitVarInsn(Opcodes.ISTORE, 2);
            mv.visitVarInsn(Opcodes.ILOAD, 1);//loads variable from top of stack to available pool, expects integer
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitInsn(Opcodes.IDIV); //divides two available integers
            mv.visitVarInsn(Opcodes.ISTORE, 3);
            
            //long section
            mv.visitLdcInsn(new Long(25L));//Ldc instruction, push a long value
            mv.visitVarInsn(Opcodes.LSTORE, 4);
            mv.visitLdcInsn(new Long(5L));//
            mv.visitVarInsn(Opcodes.LSTORE, 6);
            mv.visitVarInsn(Opcodes.LLOAD, 4);
            mv.visitVarInsn(Opcodes.LLOAD, 6);
            mv.visitInsn(Opcodes.LDIV);
            mv.visitVarInsn(Opcodes.LSTORE, 8);
            
           //double sections
            mv.visitLdcInsn(new Double(64.8D));
            mv.visitVarInsn(Opcodes.DSTORE, 10);
            mv.visitLdcInsn(new Double(8.2D));//
            mv.visitVarInsn(Opcodes.DSTORE, 12);
            mv.visitVarInsn(Opcodes.DLOAD, 10);
            mv.visitVarInsn(Opcodes.DLOAD, 12);
            mv.visitInsn(Opcodes.DDIV); //divides two available doubles
            mv.visitVarInsn(Opcodes.DSTORE, 14);
            
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"Subtraction.class");
        
        System.out.println("Done!");
        
    }//end main
}//end class