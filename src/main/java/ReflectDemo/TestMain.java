package ReflectDemo;

import java.lang.reflect.*;

public class TestMain {

    public static void main(String[] args) {

        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
        test6();

    }

    /**
     * 获取类的全类名
     * 1.Class.forName(类的完整限定名)  来加载类
     * 2.Student.class  任何类名.class 都是获取了Class类
     * 3.new Student().getClass()  任何对象.getClass()都是获取了Class类
     */
    public static void test1(){
        System.out.println("--------------获取全类名---------------");
        try {
            System.out.println(Class.forName("ReflectDemo.Student"));
            System.out.println(Student.class.getName());
            System.out.println(new Student().getClass().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取包名，类名，访问修饰符
     */
    public static void test2(){
        System.out.println("-------------包名，类名，访问修饰符-------------");
        try {
            Class c = Class.forName("ReflectDemo.Student");

            System.out.println("包名："+c.getPackage().getName());
            System.out.println("全类名："+c.getName());
            System.out.println("简类名："+c.getSimpleName());
            int num=c.getModifiers();
            System.out.println("public对应的数字："+num);
            System.out.println(Modifier.toString(num));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取类中所有属性的信息
     */
    public static void test3(){
        try {
            Class c = Class.forName("ReflectDemo.Student");
            Field[] field = c.getDeclaredFields();
            for (int i = 0; i < field.length; i++) {
                System.out.println(field[i]);
            }
            for (int i = 0; i < field.length; i++) {
                System.out.println(field[i].getModifiers());
                System.out.println(Modifier.toString(field[i].getModifiers()));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取类中所有属性的信息
     * 1.getMethod(方法名称，参数类型)  只能是公有方法public
     * 2.getMethods() 只能是公有方法public
     * 3.getDeclaredMethods() 所有方法
     */
    public static void test4(){
        try {
            Class c = Class.forName("ReflectDemo.Student");
            Method[] method = c.getDeclaredMethods();
            for (int i = 0; i < method.length; i++) {
                System.out.println("方法的名字是："+method[i].getName());
                System.out.println("方法的修饰符数值是："+method[i].getModifiers());
                System.out.println("方法的修饰符是："+Modifier.toString(method[i].getModifiers()));
                System.out.println("方法的返回值类型是："+method[i].getReturnType());
                System.out.println("-----------------------------------------------------");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取类中所有构造方法的信息
     */
    public static void test5(){
        try {
            Class c = Class.forName("ReflectDemo.Student");
            Constructor[] cons = c.getDeclaredConstructors();
            for (int i = 0; i < cons.length; i++) {
                System.out.println("构造方法的名字是："+cons[i].getName());
                System.out.println("构造方法的修饰符数值是："+cons[i].getModifiers());
                System.out.println("构造方法的修饰符是："+Modifier.toString(cons[i].getModifiers()));
                System.out.println("构造方法的参数个数是："+cons[i].getParameterCount());
                System.out.println("-----------------------------------------------------");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取类中私有的属性和方法并且执行
     */
    public static void test6(){
        try {
            Class c = Class.forName("ReflectDemo.Student");
            Student student= (Student) c.newInstance();
            Field field=c.getDeclaredField("userName");
            String name=field.getName();
            System.out.println("字段的名称是："+name);
            field.setAccessible(true);
            System.out.println("字段的值是："+field.get(student));

            Method method = c.getDeclaredMethod("getnum");
            method.setAccessible(true);
            double result = (Double)method.invoke(student);
            System.out.println(result);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
