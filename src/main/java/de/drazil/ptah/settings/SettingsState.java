
package de.drazil.ptah.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.util.Key;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@State(
        name = "org.intellij.sdk.settings.SettingsState",
        storages = @Storage(StoragePathMacros.WORKSPACE_FILE)
)
public class SettingsState implements PersistentStateComponent<SettingsState> {

    public String pathToGeneratorExecutable = "<change me>";
    public String pathToConfigFile = "<change me>";
    public String pathToTemplateFolder = "templates/";
    public String pathToOutputFolder = "<change me>";
    public boolean purgeOutFolders = false;
    public boolean rebuildOnConfigModification = false;


    public static SettingsState getInstance() {
        return ProjectManager.getInstance().getDefaultProject().getService(SettingsState.class);
        //return ApplicationManager.getApplication().getService(SettingsState.class);
    }

    @Nullable
    @Override
    public SettingsState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull SettingsState state) {
        XmlSerializerUtil.copyBean(state, this);
    }

}
