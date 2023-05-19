package de.drazil.ptah;

import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.externalSystem.dependency.analyzer.DependencyAnalyzerDependency;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.VerticalFlowLayout;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBTextField;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ptah implements ToolWindowFactory {

    private JButton button;
    private JBTextField configFileTextField;
    private JBTextField inputFolderTextField;
    private JBTextField outputFolderTextField;
    private JBCheckBox purgeOutputFoldersCheckBox;
    private JBCheckBox rebuildOnConfigModificationCheckBox;
    private Project project;

    @Override
    public void createToolWindowContent(com.intellij.openapi.project.Project project, com.intellij.openapi.wm.ToolWindow toolWindow) {
        this.project = project;

        JPanel panel = new JPanel();
        panel.setLayout(new VerticalFlowLayout());
        panel.add(getConfigFileTextField());
        panel.add(getInputFolderTextField());
        panel.add(getOutputFolderTextField());
        panel.add(getPurgeOutputFoldersCheckBox());
        panel.add(getRebuildOnConfigModificationCheckBox());
        toolWindow.getComponent().add(panel);
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
            purgeOutputFoldersCheckBox = new JBCheckBox("Purge folders before build?");
        }
        return purgeOutputFoldersCheckBox;
    }

    private JBCheckBox getRebuildOnConfigModificationCheckBox() {
        if (rebuildOnConfigModificationCheckBox == null) {
            rebuildOnConfigModificationCheckBox = new JBCheckBox("Rebuild on change config?");
        }
        return rebuildOnConfigModificationCheckBox;
    }

    private JButton getButton() {
        if (button == null) {
            button = new JButton("Click me!");
            button.setEnabled(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Messages.showInfoMessage("Clicked", "Info");
                    ConsoleView consoleView = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
                    consoleView.print("Button Clicked", ConsoleViewContentType.NORMAL_OUTPUT);
                }
            });
        }
        return button;
    }
}

