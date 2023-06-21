package de.drazil.ptah.settings;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;


public class PtahSettingsComponent {
    private JPanel mainPanel;
    private TextFieldWithBrowseButton pathToGeneratorExecutableTextField;
    private TextFieldWithBrowseButton outputFolderTextField;
    private TextFieldWithBrowseButton templateFolderTextField;
    private JBTextField projectPathTextField;
    private JBTextField configFileTextField;
    private JBCheckBox purgeOutputFoldersCheckBox;
    private JBCheckBox rebuildOnConfigModificationCheckBox;
    private JBCheckBox confirmActionsCheckBox;
    private FileChooserDescriptor descriptor;

    public PtahSettingsComponent() {

        mainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent("Project path", getProjectPathTextField(), 1, true)
                .addLabeledComponent("Executable", getPathToGeneratorExecutableTextField(), 1, true)
                .addLabeledComponent("Configuration file", getConfigFileTextField(), 1, true)
                .addLabeledComponent("Templatefolder", getTemplateFolderTextField(), 1, true)
                //.addLabeledComponent("Outputfolder", getOutputFolderTextField(), 1, true)
                .addComponent(getPurgeOutputFoldersCheckBox(), 0)
                .addComponent(getRebuildOnConfigModificationCheckBox(), 0)
                .addComponent(getConfirmActionsCheckBox(), 0)
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
            projectPathTextField.setText(project.getBasePath()+"/");
            projectPathTextField.setEnabled(false);
        }
        return projectPathTextField;
    }

    private JBTextField getConfigFileTextField() {
        if (configFileTextField == null) {
            configFileTextField = new JBTextField();
        }
        return configFileTextField;
    }

    private TextFieldWithBrowseButton getTemplateFolderTextField() {
        if (templateFolderTextField == null) {
            templateFolderTextField = new TextFieldWithBrowseButton(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    FileChooserDescriptor fileDescriptor = new FileChooserDescriptor(false, true, false, false, false, false);
                    fileDescriptor.setShowFileSystemRoots(true);
                    fileDescriptor.setTitle("Configure Path");
                    fileDescriptor.setDescription("Configure path to template folder");
                    VirtualFile currentFile = LocalFileSystem.getInstance().findFileByPath(templateFolderTextField.getText());

                    FileChooser.chooseFiles(
                            fileDescriptor,
                            null,
                            Optional.ofNullable(currentFile).orElse(null),
                            files -> {
                                String path = files.get(0).getPath();
                                templateFolderTextField.setText(path);
                            }
                    );
                }
            });
        }
        return templateFolderTextField;
    }

    private TextFieldWithBrowseButton getOutputFolderTextField() {
        if (outputFolderTextField == null) {
            outputFolderTextField = new TextFieldWithBrowseButton(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    FileChooserDescriptor fileDescriptor = new FileChooserDescriptor(false, true, false, false, false, false);
                    fileDescriptor.setShowFileSystemRoots(true);
                    fileDescriptor.setTitle("Configure Path");
                    fileDescriptor.setDescription("Configure path to output folder");
                    VirtualFile currentFile = LocalFileSystem.getInstance().findFileByPath(outputFolderTextField.getText());

                    FileChooser.chooseFiles(
                            fileDescriptor,
                            null,
                            Optional.ofNullable(currentFile).orElse(null),
                            files -> {
                                String path = files.get(0).getPath();
                                outputFolderTextField.setText(path);
                            }
                    );
                }
            });
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
            rebuildOnConfigModificationCheckBox = new JBCheckBox("Automatic rebuild on change config");
        }
        return rebuildOnConfigModificationCheckBox;
    }

    private JBCheckBox getConfirmActionsCheckBox() {
        if (confirmActionsCheckBox == null) {
            confirmActionsCheckBox = new JBCheckBox("Confirm actions");
        }
        return confirmActionsCheckBox;
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
        return  getTemplateFolderTextField().getText();
    }

    @NotNull
    public String getPathToOutputFolderText() {
        return  getOutputFolderTextField().getText();
    }

    @NotNull
    public String getPathToProjectFolderText() {
        return getProjectPathTextField().getText();
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

    public void setProjectFolderText(@NotNull String newText) {
        getProjectPathTextField().setText(newText);
    }

    public void setPurgeOutputFolders(boolean newStatus) {
        getPurgeOutputFoldersCheckBox().setSelected(newStatus);
    }

    public boolean isPurgeOutFolders() {
        return getPurgeOutputFoldersCheckBox().isSelected();
    }

    public boolean isRebuildOnConfigModification() {
        return getRebuildOnConfigModificationCheckBox().isSelected();
    }

    public boolean isConfirmActions() {
        return getConfirmActionsCheckBox().isSelected();
    }


    public void setPurgeFolders(boolean newStatus) {
        getRebuildOnConfigModificationCheckBox().setSelected(newStatus);
    }

    public void setRebuildOnConfigModification(boolean newStatus) {
        getRebuildOnConfigModificationCheckBox().setSelected(newStatus);
    }

    public void setConfirmActions(boolean newStatus) {
        getConfirmActionsCheckBox().setSelected(newStatus);
    }

}
