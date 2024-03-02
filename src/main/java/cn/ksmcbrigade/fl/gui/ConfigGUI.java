package cn.ksmcbrigade.fl.gui;

import cn.ksmcbrigade.fl.FunctionList;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.ProgressOption;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

import java.io.IOException;

public class ConfigGUI extends Screen {

    private OptionsList optionsList;

    public ConfigGUI() {
        super(new TranslatableComponent("fl.name"));
    }

    @Override
    protected void init() {

        this.optionsList = new OptionsList(
                this.minecraft, this.width, this.height,
                24,
                this.height - 32,
                25
        );

        this.optionsList.addBig(new ProgressOption("RGB",-240000F,10000.0F,1.0F,(p_168123_) -> (double) FunctionList.DefColor, (p_168226_, p_168227_) -> FunctionList.DefColor = p_168227_.intValue(), (p_168274_, p_168275_) -> {
            double d0 = p_168275_.get(p_168274_);
            return new TranslatableComponent("options.generic_value", Component.nullToEmpty("RGB"), d0);
        }));

        this.addWidget(this.optionsList);

        this.addRenderableWidget(new Button((this.width - 200) / 2,this.height - 25,200,20, CommonComponents.GUI_DONE,(p_96057_) -> this.onClose()));
        super.init();
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks){
        this.renderBackground(poseStack);
        this.optionsList.render(poseStack,mouseX,mouseY,partialTicks);
        drawCenteredString(poseStack, font,this.title.getString(),this.width / 2, 8, 16777215);
        super.render(poseStack,mouseX,mouseY,partialTicks);
    }

    @Override
    public void onClose() {
        try {
            FunctionList.save();
            super.onClose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
