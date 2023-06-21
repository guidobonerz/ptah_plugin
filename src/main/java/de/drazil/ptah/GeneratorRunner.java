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

        new Thread(new Runnable() {
            @Override
            public void run() {

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

                    ProcessOutput outpout = ExecUtil.execAndGetOutput(command);
                    System.out.println(outpout.toString());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

    }
}

