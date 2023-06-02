package de.drazil.ptah;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import com.intellij.util.messages.MessageBus;
import de.drazil.ptah.settings.PtahProjectSettings;
import de.drazil.ptah.settings.PtahProjectSettingsProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ConfigListener implements BulkFileListener {

    @Override
    public void after(@NotNull List<? extends VFileEvent> events) {
        if (events != null && events.size() > 0) {
            VFileEvent event = events.get(0);
            if (event.isFromSave()) {
                VirtualFile vf = event.getFile();
                DataContext dataContext = DataManager.getInstance().getDataContext();
                Project project = (Project) dataContext.getData(PlatformDataKeys.PROJECT);
                PtahProjectSettingsProvider provider = ServiceManager.getService(project, PtahProjectSettingsProvider.class);
                PtahProjectSettings settings = provider.getState();
                String ptahExecutable = settings.pathToGeneratorExecutable;
                String configFile = String.format("%s/%s", project.getBasePath(), settings.pathToConfigFile);
                if (configFile.equals(vf.getPath())) {
                    MessageBus messageBus = project.getMessageBus();
                    PtahActionTopic publisher = messageBus.syncPublisher(PtahActionTopic.RUN_CODE_GENERATOR_TOPIC);
                    publisher.generateCode();
                }
            }
        }
    }
}
