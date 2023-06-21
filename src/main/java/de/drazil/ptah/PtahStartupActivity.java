package de.drazil.ptah;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.execution.util.ExecUtil;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.util.messages.MessageBus;
import de.drazil.ptah.settings.PtahProjectSettings;
import de.drazil.ptah.settings.PtahProjectSettingsProvider;
import org.jetbrains.annotations.NotNull;

public class PtahStartupActivity implements StartupActivity {

    @Override
    public void runActivity(@NotNull Project project) {
        MessageBus mb = project.getMessageBus();
        mb.connect().subscribe(PtahActionTopic.RUN_CODE_GENERATOR_TOPIC, new PtahActionTopic() {
            @Override
            public void generateCode() {

                DataContext dataContext = DataManager.getInstance().getDataContext();
                Project project = (Project) dataContext.getData(PlatformDataKeys.PROJECT);
                PtahProjectSettingsProvider provider = ServiceManager.getService(project, PtahProjectSettingsProvider.class);
                PtahProjectSettings settings = provider.getState();
                String ptahExecutable = settings.pathToGeneratorExecutable;
                String configFile = String.format("%s/%s", project.getBasePath(), settings.pathToConfigFile);


                //System.out.printf("config: %s\n",project.getBasePath() + "/" + settings.pathToConfigFile);
                //System.out.printf("input: %s\n",project.getBasePath() + "/" + settings.pathToTemplateFolder);
                //System.out.printf("output: %s\n",project.getBasePath());

                var executor = java.util.concurrent.Executors.newSingleThreadExecutor();

                var processBuilder = new ProcessBuilder();

                //processBuilder.command("cmd.exe", "/c", "ping -n 3 google.com");
                processBuilder.command(ptahExecutable, "-cf", project.getBasePath() + "/" + settings.pathToConfigFile, "-ibf", project.getBasePath() + "/" + settings.pathToTemplateFolder, "-obf", project.getBasePath(), "-p", "false");
                //processBuilder.command("cmd.exe", "/C", "echo", "This is ProcessBuilder Example from JCG");//, "-cf", project.getBasePath() + "/" + settings.pathToConfigFile, "-ibf", project.getBasePath() + "/" + settings.pathToTemplateFolder, "-obf", project.getBasePath(), "-p", "false");
                GeneralCommandLine command = new GeneralCommandLine();
                command.setExePath(ptahExecutable);
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                ProcessOutput outpout = ExecUtil.execAndGetOutput(command);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }).start();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
    }
}
