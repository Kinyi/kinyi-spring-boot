package com.dataw.rhino.bytecode.agent;

import com.dataw.rhino.bytecode.Base;
import com.dataw.rhino.bytecode.MyTransformer;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * Agent-Class: com.dataw.rhino.bytecode.agent.MyAgent
 * <p>
 * jar cvfm agent.jar agent/MANIFEST.MF agent
 *
 * @author Kinyi_Chan
 * @since 2022-09-14
 */
public class MyAgent {

    public static void agentmain(String args, Instrumentation inst) {
        // 指定我们自己定义的Transformer，在其中利用Javassist做字节码替换
        inst.addTransformer(new MyTransformer(), true);
        try {
            // 重定义类并载入新的字节码
            inst.retransformClasses(Base.class);
            System.out.println("agent load done.");
        } catch (UnmodifiableClassException e) {
            System.out.println("agent load fail");
        }
    }

//    public static void premain(String args, Instrumentation inst) {
//        // 指定我们自己定义的Transformer，在其中利用Javassist做字节码替换
//        inst.addTransformer(new MyTransformer(), true);
//        try {
//            // 重定义类并载入新的字节码
//            inst.retransformClasses(Base.class);
//            System.out.println("agent load done.");
//        } catch (UnmodifiableClassException e) {
//            System.out.println("agent load fail");
//        }
//    }
}
