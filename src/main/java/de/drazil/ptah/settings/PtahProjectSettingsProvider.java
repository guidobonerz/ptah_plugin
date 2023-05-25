
package de.drazil.ptah.settings;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@State(
        name = "SettingsState",
        storages = {
                @Storage("/ptah.xml")
        }
)
public class PtahProjectSettingsProvider implements PersistentStateComponent<PtahProjectSettings> {

    public static PtahProjectSettingsProvider getInstance(Project project) {
        return new PtahProjectSettingsProvider();
    }

    @Nullable
    @Override
    public PtahProjectSettings getState() {
        return new PtahProjectSettings();
    }

    @Override
    public void loadState(@NotNull PtahProjectSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

}
