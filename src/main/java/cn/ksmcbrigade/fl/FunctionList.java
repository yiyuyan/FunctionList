package cn.ksmcbrigade.fl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Mod("fl")
@Mod.EventBusSubscriber
public class FunctionList {

    public static Integer[] colors;

    static {
        try {
            colors = getColors();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static int color = -1;
    public static int DefColor = Color.WHITE.getRGB();
    public static boolean closed = false;
    public static final Path path = Paths.get("config/fl-config.json");
    public static Thread thread = new Thread(new ColorThread(),"ColorThread-"+System.nanoTime());

    public FunctionList() throws IOException {
        MinecraftForge.EVENT_BUS.register(this);
        init();
        thread.start();
    }

    public static void init() throws IOException {
        if(!new File("config/vm/mods").exists()){
            new File("config/vm/mods").mkdirs();
        }
        if(!new File("config/vm/mods/FunctionsList.json").exists()){
            JsonObject object = new JsonObject();
            object.addProperty("name","fl.name");
            object.addProperty("id","nf");
            object.addProperty("main","cn.ksmcbrigade.fl.Manager");
            object.addProperty("function","NONE");
            object.addProperty("function_2","run");
            object.addProperty("gui_main","cn.ksmcbrigade.fl.Manager");
            object.addProperty("gui_function","getScreen");
            Files.write(Paths.get("config/vm/mods/FunctionsList.json"),object.toString().getBytes());
        }
        if(!new File("config/vm/mods/RainbowUI.json").exists()){
            JsonObject object = new JsonObject();
            object.addProperty("name","fl.rbg.name");
            object.addProperty("id","nf");
            object.addProperty("main","cn.ksmcbrigade.fl.Manager");
            object.addProperty("function","NONE");
            object.addProperty("function_2","runRainbow");
            object.addProperty("gui_main","NONE");
            object.addProperty("gui_function","NONE");
            Files.write(Paths.get("config/vm/mods/RainbowUI.json"),object.toString().getBytes());
        }

        if(!new File("config/fl-config.json").exists()){
            JsonObject object = new JsonObject();
            object.addProperty("rgb",DefColor);
            Files.write(path,object.toString().getBytes());
        }
        DefColor = JsonParser.parseString(Files.readString(path)).getAsJsonObject().get("rgb").getAsInt();
    }

    public static Integer[] getColors() throws IllegalAccessException {
        ArrayList<Integer> colors = new ArrayList<>();
        Field[] fields = Color.class.getDeclaredFields();
        for(Field field:fields){
            if(Modifier.isStatic(field.getModifiers()) && field.getType().equals(Color.class)){
                colors.add(((Color)field.get(null)).getRGB());
            }
        }
        return colors.toArray(new Integer[0]);
    }

    public static void save() throws IOException {
        JsonObject object = new JsonObject();
        object.addProperty("rgb",DefColor);
        Files.write(path,object.toString().getBytes());
    }

    public static class ColorThread implements Runnable{

        @Override
        public void run() {
            System.out.println("ColorThread is running...");
            while (!closed) {
                color++;
                try {
                    Thread.sleep(115);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
