package com.addie.puddlejumper;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class PuddleJumperKeybinds {
    public static KeyBinding MOVE_UP;
    public static KeyBinding MOVE_DOWN;

    public static void register() {
        MOVE_UP = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.stargate-network.puddle_jumper_up",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_SPACE,
                "key.category.stargate-network"
        ));

        MOVE_DOWN = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.stargate-network.puddle_jumper_down",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_SHIFT,
                "key.category.stargate-network"
        ));
    }
}