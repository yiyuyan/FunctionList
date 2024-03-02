package cn.ksmcbrigade.fl;

import cn.ksmcbrigade.fl.gui.ConfigGUI;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.entity.player.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Manager {
    public static void run(Player player){
        //Use Overlay
    }

    public static Screen getScreen(){
        return new ConfigGUI();
    }

    public static void runRainbow(Player player) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(!is(I18n.get("fl.name"))){
            set(I18n.get("fl.name"),true);
        }
        //Use Overlay
    }

    public static boolean is(String name) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName("cn.ksmcbrigade.VM.Utils");
        Class<?>[] parameterTypes = new Class[]{String.class};
        Method method = clazz.getDeclaredMethod("isEnabledMod", parameterTypes);
        method.setAccessible(true);
        Object instance = clazz.getDeclaredConstructor().newInstance();
        Object obj = method.invoke(instance, name);
        if(obj==null){
            return false;
        }
        else{
            return (boolean)obj;
        }
    }

    public static void set(String name,boolean enabled) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName("cn.ksmcbrigade.VM.Utils");
        Class<?>[] parameterTypes = new Class[]{String.class,boolean.class};
        Method method = clazz.getDeclaredMethod("SetMod", parameterTypes);
        method.setAccessible(true);
        Object instance = clazz.getDeclaredConstructor().newInstance();
        method.invoke(instance, name,enabled);
    }

    public static String[] getMods() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName("cn.ksmcbrigade.VM.Utils");
        Method method = clazz.getDeclaredMethod("getMods");
        method.setAccessible(true);
        Object instance = clazz.getDeclaredConstructor().newInstance();
        return (String[])method.invoke(instance);
    }
}
