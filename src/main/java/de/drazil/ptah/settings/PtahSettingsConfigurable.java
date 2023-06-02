package de.drazil.ptah.settings;

import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


public class PtahSettingsConfigurable implements SearchableConfigurable {
    public final static String PTAH_CONFIG_FILE_CHANGED = "PTAH_CONFIG_FILE_CHANGED";
    private PtahSettingsComponent settingsComponent = new PtahSettingsComponent();
    private Project project;
    private PtahProjectSettingsProvider settings;
    private boolean aBoolean;

    public PtahSettingsConfigurable(Project project) {
        settings = PtahProjectSettingsProvider.getInstance(project);
    }

    @Override
    public @NotNull @NonNls String getId() {
        return "de.drazil.ptah";
    }

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
        PtahProjectSettings ps = settings.getState();
        boolean modified = false;

        modified |= settingsComponent.getPathToConfigFileText().equals(ps.pathToConfigFile);
        modified |= settingsComponent.getPathToInputFolderText().equals(ps.pathToTemplateFolder);
        modified |= settingsComponent.getPathToOutputFolderText().equals(ps.pathToOutputFolder);
        modified |= settingsComponent.isPurgeOutFolders() == ps.purgeOutFolders;
        modified |= settingsComponent.isRebuildOnConfigModification() == ps.rebuildOnConfigModification;
        modified |= settingsComponent.isConfirmActions() == ps.rebuildOnConfigModification;
        return modified;
    }

    @Override
    public void apply() {
        PtahProjectSettings ps = settings.getState();
        ps.pathToGeneratorExecutable = settingsComponent.getPathToGeneratorExecutableText();
        ps.pathToConfigFile = settingsComponent.getPathToConfigFileText();
        ps.pathToTemplateFolder = settingsComponent.getPathToInputFolderText();
        ps.pathToOutputFolder = settingsComponent.getPathToOutputFolderText();
        ps.purgeOutFolders = settingsComponent.isPurgeOutFolders();
        ps.rebuildOnConfigModification = settingsComponent.isRebuildOnConfigModification();
        ps.confirmActions = settingsComponent.isConfirmActions();
    }

    @Override
    public void reset() {
        PtahProjectSettings ps = settings.getState();
        settingsComponent.setPathToGeneratorExecutableText(ps.pathToGeneratorExecutable);
        settingsComponent.setConfigFileText(ps.pathToConfigFile);
        settingsComponent.setInputFolderText(ps.pathToTemplateFolder);
        settingsComponent.setOutputFolderText(ps.pathToOutputFolder);
        settingsComponent.setPurgeOutputFolders(ps.purgeOutFolders);
        settingsComponent.setRebuildOnConfigModification(ps.rebuildOnConfigModification);
        settingsComponent.setConfirmActions(ps.confirmActions);
    }


    @Override
    public void disposeUIResources() {
        settingsComponent = null;
    }
}
