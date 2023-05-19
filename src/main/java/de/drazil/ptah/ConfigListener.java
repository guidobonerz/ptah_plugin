package de.drazil.ptah;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
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

                System.out.printf("project:%s / %s has been changed.\n", project.getName(), vf.getName());
            }
        }
    }
}
