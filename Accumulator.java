import static utils.Utilities.writeFile;

import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;
/**
 * The accumulator class accepts integer inputs and adds them together a set number of times.
 * 
 * @authors Drake Hovsepian, Spencer Childers
 *
 */
public class Accumulator{

    public static void main(String[] args){

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"AccumulatorText", null, "java/lang/Object",null);
        
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
            mv.visitTypeInsn(Opcodes.NEW, "java/util/Scanner");//Creating a scanner object
            mv.visitInsn(Opcodes.DUP);//Duplicates top value of the stack
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);
            mv.visitVarInsn(Opcodes.ASTORE, 1);//Stores the scanner object at memory location 1.
            mv.visitInsn(Opcodes.ICONST_0);//Initializing accumulator total sum value to 0.
            mv.visitVarInsn(Opcodes.ISTORE, 2);//Storing the total sum int on the stack.
            mv.visitInsn(Opcodes.ICONST_0);//Initializing the accumulator counter to 0.
            mv.visitVarInsn(Opcodes.ISTORE, 3);//Storing the counter int to the stack.
            Label l0 = new Label();//Creating label l0.
            mv.visitLabel(l0);//Call position for l0
            mv.visitFrame(Opcodes.F_APPEND,3, new Object[] {"java/util/Scanner", Opcodes.INTEGER, Opcodes.INTEGER}, 0, null);
            mv.visitVarInsn(Opcodes.ILOAD, 3);//Pulling item at memory location 3.
            mv.visitInsn(Opcodes.ICONST_5);//Creating int = 5
            Label l1 = new Label();//Creating label l1.
            mv.visitJumpInsn(Opcodes.IF_ICMPGE, l1);//While loop parameter. Repeats while ILOAD, 3 (counter) is less than 5. 
            										//If value loaded from location 3 is greater than 5, go to position l1.
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//Prepares the printer to be used
            mv.visitLdcInsn("Type an integer to add to the stack.");//Content to be printed
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);//Prints out above line 
            mv.visitVarInsn(Opcodes.ALOAD, 1);//Loads the scanner 
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false);//Calls scanner to read user input.
            mv.visitVarInsn(Opcodes.ISTORE, 4);//Stores value read by scanner in previous step.
            mv.visitVarInsn(Opcodes.ILOAD, 2);//Loads the value at memory location 2. This is the sum of the stack.
            mv.visitVarInsn(Opcodes.ILOAD, 4);//Loads the value at memory location 4. This is the value given by the user to add to the accumulator.
            mv.visitInsn(Opcodes.IADD);//Adds both items from memory locations 4 and 2.
            mv.visitVarInsn(Opcodes.ISTORE, 2);//Stores the new sum of the accumulator in memory location 2. 
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//Prepares printer.
            mv.visitVarInsn(Opcodes.ILOAD, 2);//Pulls item at memory location 2. This is the sum of the accumulator.
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);//Prints item at location 2.
            mv.visitIincInsn(3, 1);//Increments item at location 3 (the counter) by +1
            mv.visitJumpInsn(Opcodes.GOTO, l0);//Jumping back to top of while loop
            mv.visitLabel(l1);//Call position for l1
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(3, 5);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"AccumulatorText.class");
        
        System.out.println("Done!");
    }//end main
}//end class
