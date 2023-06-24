package de.drazil.ptah;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.util.messages.MessageBus;
import org.jetbrains.annotations.NotNull;

public class PtahStartupActivity implements StartupActivity {

    @Override
    public void runActivity(@NotNull Project project) {
        MessageBus mb = project.getMessageBus();
        mb.connect().subscribe(PtahActionTopic.RUN_CODE_GENERATOR_TOPIC, new PtahActionTopic() {
            @Override
            public void generateCode() {


            }
        });
    }
}
