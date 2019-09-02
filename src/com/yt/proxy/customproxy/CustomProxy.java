package com.yt.proxy.customproxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 手撸动态代理
 */
public class CustomProxy {

    public static final String LN = "\r\n";
    public static Object newProxyInstance(CustomClassLoader customClassLoader, Class<?> [] interfaces, CustomeInvocationHandle customeInvocationHandle){
        try {
            //1.动态生成java文件
            String src = generateSrc(interfaces);

            //2.java文件输出磁盘
            String filePath = CustomProxy.class.getResource("").getPath();
            File file = new File(filePath + "$Proxy3.java");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(src);
            fileWriter.flush();
            fileWriter.close();

            //3.将生成的java文件编译成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(null, null,null);
            Iterable<? extends JavaFileObject> javaFileObjects = standardFileManager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = compiler.getTask(null, standardFileManager, null, null, null, javaFileObjects);
            task.call();
            standardFileManager.close();

            //4.将编译好的.class文件加载到jvm中
            Class<?> proxyClass = customClassLoader.findClass("$Proxy3");
            Constructor<?> constructor = proxyClass.getConstructor(CustomeInvocationHandle.class);
           // file.delete();

            //5.返回字节码重组后的新的代理对象
            Object obj = constructor.newInstance(customeInvocationHandle);
            return obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成源代码
     * @param interfaces
     */
    private static String generateSrc(Class<?>[] interfaces) {
        StringBuffer sb = new StringBuffer();
        sb.append("package com.yt.proxy.customproxy;" + LN);
        sb.append("import java.lang.reflect.Method;" + LN);
        sb.append("public class $Proxy3 implements " + interfaces[0].getName() + "{" +LN);
        sb.append("CustomeInvocationHandle h;" + LN);
        sb.append("public $Proxy3(CustomeInvocationHandle h){" + LN);
        sb.append("this.h = h;" + LN);
        sb.append("}" + LN);
        for (Method method : interfaces[0].getMethods()) {
            sb.append("public  " + method.getReturnType().getName() + " " + method.getName() + "(){" + LN);
                sb.append("try{" + LN);
                sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\""+ method.getName() + "\",new Class[]{});" + LN);
                sb.append("this.h.invoke(this, m, null);" + LN);
                sb.append("} catch (Throwable e){" + LN);
                sb.append("e.printStackTrace();" + LN);
                sb.append("}" + LN);
            sb.append("}" + LN);
        }
        sb.append("}" + LN);
        return sb.toString();
    }
}
