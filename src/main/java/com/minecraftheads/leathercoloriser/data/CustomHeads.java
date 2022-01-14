package com.minecraftheads.leathercoloriser.data;

import com.minecraftheads.pluginUtils.heads.HeadCreator;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum CustomHeads {
    RANDOM("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZThhNDZjNGM1NWFlNzFmMzk3ZjZlMDk2ZWFmNzFkZDdhNmQzYmZlYTQzMjI5OTgxNDkzZDgxYzc2NGJiOTIyMCJ9fX0="),
    INCREASE_HUE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWJjNmYxZjQ1NjEzYzIxY2VkOWIxYmJkYWI4YzU4NTg5MDNlZmNkMmVjYjU3MDUzNmY0YWFhMDJlZDU0OWNiMSJ9fX0="),
    DECREASE_HUE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM1MzllMGFjMTMzMjc3ZmJkZDA2NGJiMDU3MTM2N2Q2ZmQ2M2FlYjgzODEwM2UzM2VmMjBiOGVlNjYxNTY4OSJ9fX0="),
    INCREASE_SATURATION("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWYwZDBkMThiOWFkNDI3MjJiMjkxNWRjYzEwMTJjY2Y3MTkwN2U5ZGRiMmRlNzRlYzI1YTI0YTVkZjBjZGI2In19fQ=="),
    DECREASE_SATURATION("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjMxY2RlMjNiOTFmMTRhMmRjN2UwZjNkM2Y0NDhmYmZjZTZjZjhjZTJkYjc4ZTBhNWJkNTNiOWMwMWNmOGZlZiJ9fX0="),
    INCREASE_BRIGHTNESS("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmJmY2QwZTM2NjRlZGQ1Yjk3ZjYzMDQ0MGExZTM5ZWZjYzg5OTIxYWQ3M2MxYjhiODAwMWYwMGU4YWI0YmUwMyJ9fX0="),
    DECREASE_BRIGHTNESS("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2E5NjA5OTg2YWMyNzM2YmRiODJkOWM1YTNiMGNhY2NiMWJmZjMyOGNkN2RiYjZhNGUzYzE0NDk4NmI3ZTM2MSJ9fX0="),
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