package com.github.einjerjar.mc.keymap.keys.category;

import com.github.einjerjar.mc.keymap.keys.CategoryHolder;
import com.github.einjerjar.mc.keymap.keys.KeybindHolder;
import com.github.einjerjar.mc.keymap.keys.key.MalilibKeybind;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.KeybindCategory;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class MalilibCategory implements CategoryHolder {
    KeybindCategory category;
    Text categoryName;

    List<KeybindHolder> bindings = new ArrayList<>();

    public MalilibCategory(KeybindCategory category) {
        this.category = category;
        this.categoryName = new LiteralText(category.getModName());

        for (ConfigHotkey hk : category.getHotkeys().stream().filter(hotkey -> hotkey instanceof ConfigHotkey).map(hotkey -> (ConfigHotkey) hotkey).toList()) {
            MalilibKeybind mk = new MalilibKeybind(hk);
            mk.setModName(category.getModName());
            mk.category = this;
            bindings.add(mk);
        }
    }

    public void appendCategory(KeybindCategory category) {
        for (IHotkey hk : category.getHotkeys()) {
            MalilibKeybind mk = new MalilibKeybind(hk);
            mk.setModName(category.getModName());
            mk.category = this;
            bindings.add(mk);
        }
    }

    @Override
    public Text categoryName() {
        return categoryName;
    }

    @Override
    public String categoryKey() {
        return category.getModName();
    }

    @Override
    public void addKeybind(KeybindHolder kb) {
    }

    @Override
    public List<KeybindHolder> keybinds() {
        return bindings;
    }

    @Override
    public String getModName() {
        return category.getModName();
    }

    @Override
    public int size() {
        return 0;
    }
}
