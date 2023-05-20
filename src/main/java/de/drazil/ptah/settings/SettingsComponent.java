package de.drazil.ptah.settings;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.fileChooser.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import com.jetbrains.JBRFileDialog;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;


public class SettingsComponent {
    private JPanel mainPanel;
    private TextFieldWithBrowseButton pathToGeneratorExecutableTextField;
    //private JBTextField pathToGeneratorExecutableTextField;
    private JBTextField projectPathTextField;
    private JBTextField configFileTextField;
    private JBTextField templateFolderTextField;
    private JBTextField outputFolderTextField;
    private JBCheckBox purgeOutputFoldersCheckBox;
    private JBCheckBox rebuildOnConfigModificationCheckBox;
    private FileChooserDescriptor descriptor;

    public SettingsComponent() {

        mainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent("project path", getProjectPathTextField(), 1, true)
                .addLabeledComponent("Path to executable", getPathToGeneratorExecutableTextField(), 1, true)
                .addLabeledComponent("Path to configuration file", getConfigFileTextField(), 1, true)
                .addLabeledComponent("Path to templatefolder", getTemplateFolderTextField(), 1, true)
                .addLabeledComponent("Path to outputfolder", getOutputFolderTextField(), 1, true)
                .addComponent(getPurgeOutputFoldersCheckBox(), 0)
                .addComponent(getRebuildOnConfigModificationCheckBox(), 0)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    private TextFieldWithBrowseButton getPathToGeneratorExecutableTextField() {
        if (pathToGeneratorExecutableTextField == null) {
            pathToGeneratorExecutableTextField = new TextFieldWithBrowseButton(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    FileChooserDescriptor fileDescriptor = new FileChooserDescriptor(true, false, false, false, false, false);
                    fileDescriptor.setShowFileSystemRoots(true);
                    fileDescriptor.setTitle("Configure Path");
                    fileDescriptor.setDescription("Configure path to generator executable");
                    VirtualFile currentFile = LocalFileSystem.getInstance().findFileByPath(pathToGeneratorExecutableTextField.getText());

                    FileChooser.chooseFiles(
                            fileDescriptor,
                            null,
                            Optional.ofNullable(currentFile).orElse(null),
                            files -> {
                                String path = files.get(0).getPath();
                                pathToGeneratorExecutableTextField.setText(path);
                            }
                    );


                    System.out.println("click");
                }
            });
        }
        return pathToGeneratorExecutableTextField;
    }

    private JBTextField getProjectPathTextField() {
        if (projectPathTextField == null) {
            projectPathTextField = new JBTextField();
            DataContext dataContext = DataManager.getInstance().getDataContext();
            Project project = (Project) dataContext.getData(PlatformDataKeys.PROJECT);
            projectPathTextField.setText(project.getBasePath());
            projectPathTextField.setEditable(false);
        }
        return projectPathTextField;
    }

    private JBTextField getConfigFileTextField() {
        if (configFileTextField == null) {
            configFileTextField = new JBTextField();
        }
        return configFileTextField;
    }

    private JBTextField getTemplateFolderTextField() {
        if (templateFolderTextField == null) {
            templateFolderTextField = new JBTextField();
        }
        return templateFolderTextField;
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
        return getTemplateFolderTextField().getText();
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
        getTemplateFolderTextField().setText(newText);
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
