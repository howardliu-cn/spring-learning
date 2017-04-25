package cn.howardliu.spring.boot;

import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.LoaderClassPath;
import org.apache.ibatis.javassist.NotFoundException;

import java.sql.Connection;

/**
 * <br>created at 17-4-10
 *
 * @author liuxh
 * @version 1.0.0
 * @since 1.0.0
 */
public class Main {
    public static void main(String[] args) throws NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        pool.appendPathList(System.getProperty("java.class.path"));
        pool.appendClassPath(new LoaderClassPath(ClassLoader.getSystemClassLoader()));
        CtClass ctClass = pool.get("com.mysql.jdbc.ConnectionImpl");
        System.out.println(isConnection(ctClass));
    }

    private static boolean isConnection(CtClass ctClass) {
        return isImpl(ctClass, Connection.class) || isChild(ctClass, Connection.class);
    }

    private static boolean isChild(CtClass ctClass, Class<?> clazz) {
        System.out.println(ctClass.getName());
        CtClass superclass;
        try {
            superclass = ctClass.getSuperclass();
        } catch (NotFoundException e) {
            return false;
        }

        return !(superclass == null || Object.class.getName().equals(superclass.getName()))
                &&
                (clazz.getName().equals(superclass.getName()) || isChild(superclass, clazz));
    }

    private static boolean isImpl(CtClass ctClass, Class<?> clazz) {
        System.out.println(ctClass.getName());
        CtClass[] interfaces;
        try {
            interfaces = ctClass.getInterfaces();
        } catch (NotFoundException e) {
            return false;
        }
        for (CtClass anInterface : interfaces) {
            if (clazz.getName().equals(anInterface.getName()) || isImpl(anInterface, clazz)) {
                return true;
            }
        }
        return false;
    }
}
