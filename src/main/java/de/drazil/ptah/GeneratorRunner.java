package de.drazil.ptah;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.execution.util.ExecUtil;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import de.drazil.ptah.settings.PtahProjectSettings;
import de.drazil.ptah.settings.PtahProjectSettingsProvider;

public class GeneratorRunner {

    public static void run(Project project) {


        PtahProjectSettingsProvider provider = ServiceManager.getService(project, PtahProjectSettingsProvider.class);
        PtahProjectSettings settings = provider.getState();
        String ptahExecutable = settings.pathToGeneratorExecutable;
        String configFile = String.format("%s/%s", project.getBasePath(), settings.pathToConfigFile);
        ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow("Code Generator");

        Content content = toolWindow.getContentManager().findContent("Path Console");
        ConsoleView consoleView = (ConsoleView) content.getComponent();
        consoleView.clear();
        try {
            GeneralCommandLine command = new GeneralCommandLine();
            command.setExePath(ptahExecutable);
            command.addParameter("-cf");
            command.addParameter(configFile);
            command.addParameter("-ibf");
            command.addParameter(project.getBasePath() + "/" + settings.pathToTemplateFolder);
            command.addParameter("-obf");
            command.addParameter(project.getBasePath());
            if (settings.purgeOutFolders) {
                command.addParameter("-p");
            }
            ProcessOutput output = ExecUtil.execAndGetOutput(command);
            if (output.getExitCode() == 0) {
                com.intellij.openapi.vfs.VirtualFileManager.getInstance().syncRefresh();
                consoleView.print("Code generation successful\n", ConsoleViewContentType.NORMAL_OUTPUT);
                consoleView.print(output.getStdout(), ConsoleViewContentType.NORMAL_OUTPUT);
                PtahNotifier.notify(project, "Code generation successful, see console in tool window", com.intellij.notification.NotificationType.INFORMATION);
            } else {
                consoleView.print("Code generation unsuccessful\n", ConsoleViewContentType.NORMAL_OUTPUT);
                consoleView.print(output.getStderr(), ConsoleViewContentType.NORMAL_OUTPUT);
                PtahNotifier.notify(project, "Code generation unsuccessful, see console in tool window", com.intellij.notification.NotificationType.ERROR);
            }


        } catch (Exception ex) {
            consoleView.print("Code generation unsuccessful\n", ConsoleViewContentType.NORMAL_OUTPUT);
            consoleView.print(ex.getMessage(), ConsoleViewContentType.NORMAL_OUTPUT);
            PtahNotifier.notify(project, "Code generation unsuccessful, see console in tool window", com.intellij.notification.NotificationType.ERROR);
            ex.printStackTrace();
        }
    }
}

