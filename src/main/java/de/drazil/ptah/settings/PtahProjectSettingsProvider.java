
package de.drazil.ptah.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@State(
        name = "PtahProjectSettingsProvider",
        storages = {
                @Storage("./ptah.xml")
        }
)
public class PtahProjectSettingsProvider implements PersistentStateComponent<PtahProjectSettings> {

    private PtahProjectSettings settings = new PtahProjectSettings();

    public static PtahProjectSettingsProvider getInstance(Project project) {
        return ServiceManager.getService(project, PtahProjectSettingsProvider.class);
    }

    @Nullable
    @Override
    public PtahProjectSettings getState() {
        return settings;
    }

    @Override
    public void loadState(@NotNull PtahProjectSettings state) {
        XmlSerializerUtil.copyBean(state, settings);
    }

}
