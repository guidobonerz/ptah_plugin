package de.drazil.ptah.settings;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SettingsComponent {
    private JPanel mainPanel;
    private JBTextField pathToGeneratorExecutableTextField;
    private JBTextField configFileTextField;
    private JBTextField inputFolderTextField;
    private JBTextField outputFolderTextField;
    private JBCheckBox purgeOutputFoldersCheckBox;
    private JBCheckBox rebuildOnConfigModificationCheckBox;

    public SettingsComponent() {
        mainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("Path to executable"), getPathToGeneratorExecutableTextField(), 1, true)
                .addLabeledComponent(new JBLabel("Path to configuration file"), getConfigFileTextField(), 1, true)
                .addLabeledComponent(new JBLabel("Path to inputfolder"), getInputFolderTextField(), 1, true)
                .addLabeledComponent(new JBLabel("Path to outputfolder"), getOutputFolderTextField(), 1, true)
                .addComponent(getPurgeOutputFoldersCheckBox(), 0)
                .addComponent(getRebuildOnConfigModificationCheckBox(), 0)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    private JBTextField getPathToGeneratorExecutableTextField() {
        if (pathToGeneratorExecutableTextField == null) {
            pathToGeneratorExecutableTextField = new JBTextField();
        }
        return pathToGeneratorExecutableTextField;
    }

    private JBTextField getConfigFileTextField() {
        if (configFileTextField == null) {
            configFileTextField = new JBTextField();
        }
        return configFileTextField;
    }

    private JBTextField getInputFolderTextField() {
        if (inputFolderTextField == null) {
            inputFolderTextField = new JBTextField();
        }
        return inputFolderTextField;
    }

    private JBTextField getOutputFolderTextField() {
        if (outputFolderTextField == null) {
            outputFolderTextField = new JBTextField();
        }
        return outputFolderTextField;
    }

    private JBCheckBox getPurgeOutputFoldersCheckBox() {
        if (purgeOutputFoldersCheckBox == null) {
            purgeOutputFoldersCheckBox = new JBCheckBox("Purge folders before build");
        }
        return purgeOutputFoldersCheckBox;
    }

    private JBCheckBox getRebuildOnConfigModificationCheckBox() {
        if (rebuildOnConfigModificationCheckBox == null) {
            rebuildOnConfigModificationCheckBox = new JBCheckBox("Rebuild on change config");
        }
        return rebuildOnConfigModificationCheckBox;
    }


    public JPanel getPanel() {
        return mainPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return getPathToGeneratorExecutableTextField();
    }

    @NotNull
    public String getPathToGeneratorExecutableText() {
        return getPathToGeneratorExecutableTextField().getText();
    }

    @NotNull
    public String getPathToConfigFileText() {
        return getConfigFileTextField().getText();
    }

    @NotNull
    public String getPathToInputFolderText() {
        return getInputFolderTextField().getText();
    }

    @NotNull
    public String getPathToOutputFolderText() {
        return getOutputFolderTextField().getText();
    }

    public void setPathToGeneratorExecutableText(@NotNull String newText) {
        getPathToGeneratorExecutableTextField().setText(newText);
    }

    public void setConfigFileText(@NotNull String newText) {
        getConfigFileTextField().setText(newText);
    }

    public void setInputFolderText(@NotNull String newText) {
        getInputFolderTextField().setText(newText);
    }

    public void setOutputFolderText(@NotNull String newText) {
        getOutputFolderTextField().setText(newText);
    }


    public boolean isPurgeOutFolders() {
        return getPurgeOutputFoldersCheckBox().isSelected();
    }

    public void setPurgeOutputFolders(boolean newStatus) {
        getPurgeOutputFoldersCheckBox().setSelected(newStatus);
    }

    public boolean isRebuildOnConfigModification() {
        return getRebuildOnConfigModificationCheckBox().isSelected();
    }

    public void setRebuildOnConfigModification(boolean newStatus) {
        getRebuildOnConfigModificationCheckBox().setSelected(newStatus);
    }

}
