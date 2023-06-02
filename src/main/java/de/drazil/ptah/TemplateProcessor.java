package de.drazil.ptah;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.util.messages.MessageBus;
import org.jetbrains.annotations.NotNull;

public class TemplateProcessor implements StartupActivity {

    @Override
    public void runActivity(@NotNull Project project) {
        MessageBus mb = project.getMessageBus();
        mb.connect().subscribe(RunCodeGeneratorTopic.RUN_CODE_GENERATOR_TOPIC, new RunCodeGeneratorTopic() {
            @Override
            public void generateCode() {
                System.out.println("run code generator");
            }
        });
    }
}
