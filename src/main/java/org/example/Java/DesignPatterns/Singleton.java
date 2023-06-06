package org.example.Java.DesignPatterns;

/**
 * @author liushixing
 * @date 2023/6/6 20:05
 */
public class Singleton {

    //私有的静态对象，外部类无法拿到
    private volatile static Singleton uniqueSingleton;

    //私有的构造方法，外部类无法访问
    private Singleton(){

    }

    //公共的方法，外部类可以使用该方法来获取Singleton对象
    public Singleton getUniqueSingleton(){
        //首先判断uniqueSingleton是否被实例化过，已被实例化则无法再次获取
        if(uniqueSingleton == null){
            //对Singleton类加锁
            synchronized (Singleton.class){
                //double check
                /**
                 * 线程1和线程2同时完成第一次check，而后线程2阻塞，线程1继续执行并获取到了锁，从而得到实例化的Singleton对象，然后释放锁
                 * 而后线程2恢复，继续执行，由于线程1已经释放锁，因此线程2可以成功加锁
                 * 如果不存在double check,那线程2也将获得实例化的Singleton对象，从而不符合单例模式
                 */
                if(uniqueSingleton == null){
                    uniqueSingleton = new Singleton();
                }
            }
        }
        return uniqueSingleton;
    }
}
