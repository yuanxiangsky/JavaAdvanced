package annotation;

import javax.xml.bind.Marshaller;
import java.awt.event.ActionListener;
import java.lang.reflect.*;

/**
 * Created by YuanXiang on 2017/5/27.
 */
public class ActionListenerInstaller {

    public static void processAnnotations(Object object) {
        try {
            Class<?> cl = object.getClass();
            for (Method m : cl.getDeclaredMethods()) {
                ActionListenerFor a = m.getAnnotation(ActionListenerFor.class);
                if (a != null) {
                    Field f = cl.getDeclaredField(a.soure());
                    f.setAccessible(true);
                    addListener(f.get(object),object,m);
                }


            }
        }catch (ReflectiveOperationException e){
            e.printStackTrace();
        }
    }

    private static void addListener(Object source, final Object param, final Method m) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return m.invoke(param);
            }
        };
        Object listener = Proxy.newProxyInstance(null,new Class[]{java.awt.event.ActionListener.class},handler);
        Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);
        adder.invoke(source,listener);
    }
}
