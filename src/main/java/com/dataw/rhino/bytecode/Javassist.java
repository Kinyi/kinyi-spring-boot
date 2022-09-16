package com.dataw.rhino.bytecode;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.io.IOException;

/**
 * @author Kinyi_Chan
 * @since 2022-09-14
 */
public class Javassist {

    private void modifyCode() throws NotFoundException, CannotCompileException, IOException, InstantiationException, IllegalAccessException {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.dataw.rhino.bytecode.Base");
        CtMethod m = cc.getDeclaredMethod("process");
        m.insertBefore("System.out.println(\"start\");");
        m.insertAfter("System.out.println(\"end\");");
        Class<?> c = cc.toClass();
        cc.writeFile("/tmp");
        Base base = (Base) c.newInstance();
        base.process();
    }

    public static void main(String[] args) {
        Javassist javassist = new Javassist();
        try {
            javassist.modifyCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
