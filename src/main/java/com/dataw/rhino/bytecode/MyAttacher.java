package com.dataw.rhino.bytecode;

import com.sun.tools.attach.VirtualMachine;

/**
 * @author Kinyi_Chan
 * @since 2022-09-14
 */
public class MyAttacher {

    public static void main(String[] args) throws Exception {
        // 传入目标JVM pid
        VirtualMachine vm = VirtualMachine.attach("34576");
        vm.loadAgent("/Users/Kinyi_Chan/IdeaProjects/study/dive-in-spring-boot/src/main/java/com/dataw/rhino/bytecode/agent.jar");
    }
}
