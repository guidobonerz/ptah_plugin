package de.drazil.ptah;

import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PtahTool implements ToolWindowFactory {
    private JPanel mainPanel;
    private JButton startGeneratorButton;

    private ConsoleView consoleView;

    @Override

    public void createToolWindowContent(Project project, ToolWindow toolWindow) {

        consoleView = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
        Content content = toolWindow.getContentManager().getFactory().createContent(consoleView.getComponent(), "Path Console", false);
        toolWindow.getContentManager().addContent(content);
        consoleView.print("Ptah has started", ConsoleViewContentType.NORMAL_OUTPUT);


        /*
        mainPanel = FormBuilder.createFormBuilder()
                .addComponent(getStartGeneratorButton(), 0)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
        toolWindow.getComponent().add(mainPanel);
*/
    }



    private JButton getStartGeneratorButton() {
        if (startGeneratorButton == null) {
            startGeneratorButton = new JButton("Start code generation");
            startGeneratorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DataContext dataContext = DataManager.getInstance().getDataContext();
                    Project project = (Project) dataContext.getData(PlatformDataKeys.PROJECT);

                    //MessageBus messageBus = project.getMessageBus();
                    //PtahActionTopic publisher = messageBus.syncPublisher(PtahActionTopic.RUN_CODE_GENERATOR_TOPIC);
                    //publisher.generateCode();
                    GeneratorRunner.run(project);
                }
            });
        }
        return startGeneratorButton;
    }

}

