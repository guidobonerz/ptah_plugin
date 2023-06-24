package de.drazil.ptah;

public class PtahNotifier {


    public static void notify(@javax.annotation.Nullable com.intellij.openapi.project.Project project,
                              String content, com.intellij.notification.NotificationType type) {
        com.intellij.notification.NotificationGroupManager.getInstance()
                .getNotificationGroup("PtahNotification")
                .createNotification(content, type)
                .notify(project);
    }


}
