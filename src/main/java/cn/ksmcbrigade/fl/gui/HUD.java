package cn.ksmcbrigade.fl.gui;

import cn.ksmcbrigade.fl.FunctionList;
import cn.ksmcbrigade.fl.Manager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static cn.ksmcbrigade.fl.Manager.is;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class HUD {

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void eventHandler(RenderGameOverlayEvent.Pre event) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL && is(I18n.get("fl.name"))) {
            try {
                Minecraft MC = Minecraft.getInstance();
                String[] mods = get(Manager.getMods());
                boolean rainbow = is(I18n.get("fl.rbg.name"));
                for(int i=0;i<mods.length;i++){
                    if(rainbow){
                        int color = FunctionList.color+1;
                        if(color>FunctionList.colors.length-1){
                            FunctionList.color=-1;
                            color=0;
                        }
                        MC.font.draw(event.getMatrixStack(),mods[i],0, 1+i * 10, FunctionList.colors[color]);
                    }
                    else{
                        MC.font.draw(event.getMatrixStack(),mods[i],0, i * 10, FunctionList.DefColor);
                    }
                }
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static String[] get(String[] mods) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ArrayList<String> enabledMods = new ArrayList<>();
        Arrays.sort(mods, Comparator.comparing(String::length).reversed());
        for(String name:mods){
            if(is(name)){
                enabledMods.add(name);
            }
        }
        return enabledMods.toArray(new String[0]);
    }
}
