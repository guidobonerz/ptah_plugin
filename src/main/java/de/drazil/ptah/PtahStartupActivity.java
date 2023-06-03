package de.drazil.ptah;

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

import java.io.InputStream;

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

                try {
                    ProcessBuilder builder = new ProcessBuilder();
                    builder.command(ptahExecutable, "--version");
                    Process process = builder.start();
                    int exitCode = process.waitFor();
                    InputStream in = process.getInputStream();
                    while (in.available() != 0) {
                        System.out.print((char) in.read());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
