package com.minecraftheads.leathercolorizer.data;

import com.minecraftheads.pluginUtils.heads.HeadCreator;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum CustomHeads {
    RANDOM("http://textures.minecraft.net/texture/e8a46c4c55ae71f397f6e096eaf71dd7a6d3bfea43229981493d81c764bb9220"),
    INCREASE_HUE("http://textures.minecraft.net/texture/5bc6f1f45613c21ced9b1bbdab8c5858903efcd2ecb570536f4aaa02ed549cb1"),
    DECREASE_HUE("http://textures.minecraft.net/texture/83539e0ac133277fbdd064bb0571367d6fd63aeb838103e33ef20b8ee6615689"),
    INCREASE_SATURATION("http://textures.minecraft.net/texture/1f0d0d18b9ad42722b2915dcc1012ccf71907e9ddb2de74ec25a24a5df0cdb6"),
    DECREASE_SATURATION("http://textures.minecraft.net/texture/231cde23b91f14a2dc7e0f3d3f448fbfce6cf8ce2db78e0a5bd53b9c01cf8fef"),
    INCREASE_BRIGHTNESS("http://textures.minecraft.net/texture/bbfcd0e3664edd5b97f630440a1e39efcc89921ad73c1b8b8001f00e8ab4be03"),
    DECREASE_BRIGHTNESS("http://textures.minecraft.net/texture/ca9609986ac2736bdb82d9c5a3b0caccb1bff328cd7dbb6a4e3c144986b7e361"),
    ;

    private final ItemStack itemStack;

    CustomHeads(String base64) {
        itemStack = HeadCreator.getHead(base64);
    }

    public ItemStack asItemStack() {
        return itemStack.clone();
    }

    public Material asMaterial() {
        return itemStack.getType();
    }
}