package de.drazil.ptah;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.execution.util.ExecUtil;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import de.drazil.ptah.settings.PtahProjectSettings;
import de.drazil.ptah.settings.PtahProjectSettingsProvider;

public class GeneratorRunner {

    public static void run(Project project) {


        PtahProjectSettingsProvider provider = ServiceManager.getService(project, PtahProjectSettingsProvider.class);
        PtahProjectSettings settings = provider.getState();
        String ptahExecutable = settings.pathToGeneratorExecutable;
        String configFile = String.format("%s/%s", project.getBasePath(), settings.pathToConfigFile);
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
                PtahNotifier.notify(project, "Code generation successful:"+output.getStdout(), com.intellij.notification.NotificationType.INFORMATION);
            } else {
                PtahNotifier.notify(project, "Code generation unsuccessful:"+output.getStderr(), com.intellij.notification.NotificationType.ERROR);
            }


        } catch (Exception ex) {
            PtahNotifier.notify(project, "Code generation unsuccessful:" + ex.getMessage(), com.intellij.notification.NotificationType.ERROR);
            ex.printStackTrace();
        }
    }
}

