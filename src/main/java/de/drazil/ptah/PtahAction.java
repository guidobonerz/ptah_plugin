package de.drazil.ptah;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;

public class PtahAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        DataContext dataContext = DataManager.getInstance().getDataContext();
        Project project = (Project) dataContext.getData(PlatformDataKeys.PROJECT);
        //MessageBus messageBus = project.getMessageBus();
        //PtahActionTopic publisher = messageBus.syncPublisher(PtahActionTopic.RUN_CODE_GENERATOR_TOPIC);
        //publisher.generateCode();
        GeneratorRunner.run(project);

    }
}
