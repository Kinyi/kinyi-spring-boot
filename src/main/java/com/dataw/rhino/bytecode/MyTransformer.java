package com.dataw.rhino.bytecode;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author Kinyi_Chan
 * @since 2022-09-14
 */
public class MyTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("Transforming " + className);
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.get("com.dataw.rhino.bytecode.Base");
            CtMethod m = cc.getDeclaredMethod("process");
            m.insertBefore("{System.out.println(\"start\");}");
            m.insertAfter("{System.out.println(\"end\");}");
            return cc.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
