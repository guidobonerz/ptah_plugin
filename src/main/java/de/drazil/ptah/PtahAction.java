package de.drazil.ptah;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBus;
import de.drazil.ptah.settings.PtahProjectSettings;
import de.drazil.ptah.settings.PtahProjectSettingsProvider;

import java.nio.charset.Charset;

public class PtahAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        DataContext dataContext = DataManager.getInstance().getDataContext();
        Project project = (Project) dataContext.getData(PlatformDataKeys.PROJECT);
        MessageBus messageBus = project.getMessageBus();
        PtahActionTopic publisher = messageBus.syncPublisher(PtahActionTopic.RUN_CODE_GENERATOR_TOPIC);
        publisher.generateCode();
        /*
        DataContext dataContext = DataManager.getInstance().getDataContext();
        Project project = (Project) dataContext.getData(PlatformDataKeys.PROJECT);
        PtahProjectSettingsProvider provider = ServiceManager.getService(project, PtahProjectSettingsProvider.class);
        PtahProjectSettings settings = provider.getState();
        String ptahExecutable = settings.pathToGeneratorExecutable;
        String configFile = String.format("%s/%s", project.getBasePath(), settings.pathToConfigFile);
        try {
            GeneralCommandLine command = new GeneralCommandLine();
            command.setExePath(ptahExecutable);
            command.addParameter("--version");
            ProcessHandler ph = new CapturingProcessHandler(
                    command.createProcess(),
                    Charset.defaultCharset(),
                    command.getCommandLineString());
            OSProcessHandler.checkEdtAndReadAction(ph);
        } catch (Exception ex) {
        }*/
    }
}
