package de.drazil.ptah;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;

import javax.annotation.Nullable;

public class PtahNotifier {


    public static void notify(@Nullable Project project,
                              String content, NotificationType type) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("PtahNotification")
                .createNotification(content, type)
                .notify(project);
    }


}
