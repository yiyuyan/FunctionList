package cn.ksmcbrigade.fl.mixin;

import cn.ksmcbrigade.fl.FunctionList;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class onClose {

    @Inject(method = "close",at = @At("HEAD"))
    public void close(CallbackInfo ci){
        FunctionList.closed=true;
    }
}
