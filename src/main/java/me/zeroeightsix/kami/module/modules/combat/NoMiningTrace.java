package me.zeroeightsix.kami.module.modules.combat;

import me.zeroeightsix.kami.module.Module;
import me.zeroeightsix.kami.setting.Setting;
import me.zeroeightsix.kami.setting.Settings;
import net.minecraft.item.ItemPickaxe;

/**
 * Created 16 November 2019 by hub
 * @see me.zeroeightsix.kami.mixin.client.MixinEntityRenderer
 */
@Module.Info(name = "NoMiningTrace", category = Module.Category.MISC, description = "Blocks entities from stopping you from mining")
public class NoMiningTrace extends Module {

    private Setting<Boolean> pickaxe = register(Settings.b("Pickaxe only", true));

    // we need this singleton pattern to access the isEnabled method static
    private static NoMiningTrace INSTANCE;

    public NoMiningTrace() {
        super();
        NoMiningTrace.INSTANCE = this;
    }

    // this gets called in MixinEntityRenderer
    public static boolean spoofTrace() {
        if (INSTANCE.isDisabled()) {
            return false;
        }
        if (INSTANCE.pickaxe.getValue() && !(mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe)) {
            return false;
        }
        return true;
    }

}
