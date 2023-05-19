package de.drazil.ptah.settings;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


public class SettingsConfigurable implements Configurable {

    private SettingsComponent settingsComponent = new SettingsComponent();

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "SDK: Application Settings Example";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return settingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return settingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        SettingsState settings = SettingsState.getInstance();

        boolean modified = false;
        if (settings != null) {
            modified = !settingsComponent.getPathToGeneratorExecutableText().equals(settings.pathToGeneratorExecutable);
            modified |= settingsComponent.getPathToConfigFileText().equals(settings.pathToConfigFile);
            modified |= settingsComponent.getPathToInputFolderText().equals(settings.pathToInputFolder);
            modified |= settingsComponent.getPathToOutputFolderText().equals(settings.pathToOutputFolder);
            modified |= settingsComponent.isPurgeOutFolders() == settings.purgeOutFolders;
            modified |= settingsComponent.isRebuildOnConfigModification() == settings.rebuildOnConfigModification;
        }
        return modified;
    }

    @Override
    public void apply() {
        SettingsState settings = SettingsState.getInstance();
        if (settings != null) {
            settings.pathToGeneratorExecutable = settingsComponent.getPathToGeneratorExecutableText();
            settings.pathToConfigFile = settingsComponent.getPathToConfigFileText();
            settings.pathToInputFolder = settingsComponent.getPathToInputFolderText();
            settings.pathToOutputFolder = settingsComponent.getPathToOutputFolderText();
            settings.purgeOutFolders = settingsComponent.isPurgeOutFolders();
            settings.rebuildOnConfigModification = settingsComponent.isRebuildOnConfigModification();
        }
    }

    @Override
    public void reset() {
        SettingsState settings = SettingsState.getInstance();
        if (settings != null) {
            settingsComponent.setPathToGeneratorExecutableText(settings.pathToGeneratorExecutable);
            settingsComponent.setConfigFileText(settings.pathToConfigFile);
            settingsComponent.setInputFolderText(settings.pathToInputFolder);
            settingsComponent.setOutputFolderText(settings.pathToOutputFolder);
            settingsComponent.setPurgeOutputFolders(settings.purgeOutFolders);
            settingsComponent.setRebuildOnConfigModification(settings.rebuildOnConfigModification);
        }
    }


    @Override
    public void disposeUIResources() {
        settingsComponent = null;
    }
}
