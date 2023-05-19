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
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ptah implements ToolWindowFactory {
    private JPanel mainPanel;
    private JButton startGeneratorButton;

    @Override

    public void createToolWindowContent(com.intellij.openapi.project.Project project, com.intellij.openapi.wm.ToolWindow toolWindow) {

        mainPanel = FormBuilder.createFormBuilder()
                .addComponent(getStartGeneratorButton(), 0)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
        toolWindow.getComponent().add(mainPanel);

    }

    private JButton getStartGeneratorButton() {
        if (startGeneratorButton == null) {
            startGeneratorButton = new JButton("Start code generation");
            startGeneratorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                System.out.println("generator started...");
                }
            });
        }
        return startGeneratorButton;
    }

}

