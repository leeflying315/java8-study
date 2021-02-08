package com.lifei.study.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author lifei
 * @Description: 自定义类加载器
 * @Date 2021/2/3
 */
// 自定义类加载器必须是ClassLoader的直接或者间接子类
public class MyClassLoader extends ClassLoader {
    // 自定义默认的class存放路径
    private final static Path DEFAULT_CLASS_DIR = Paths.get("D:", "classloader1");

    private final Path classDir;

    public MyClassLoader() {
        super();
        this.classDir = DEFAULT_CLASS_DIR;
    }

    // 允许传入指定路径的class路径
    public MyClassLoader(String classDir) {
        super();
        this.classDir = Paths.get(classDir);
    }

    // 重写父类的findClass方法
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 如果数据为null，或者没有读到任何信息，则抛出ClassNotFoundException异常
        byte[] classBytes = this.readClassBytes(name);
        if (classBytes.length == 0) {
            throw new ClassNotFoundException("Can not load the class" + name);
        }

        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    // Class文件读入内存
    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        String classPath = name.replace(".", "/");
        Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
        if (!classFullPath.toFile().exists()) {
            throw new ClassNotFoundException("the class " + name + " not found.");
        }
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Files.copy(classFullPath, baos);
            return baos.toByteArray();
        } catch (IOException ex) {
            throw new ClassNotFoundException("load the class " + name + " occur error.", ex);
        }
    }

    @Override
    public String toString() {
        return "My ClassLoader";
    }
}
