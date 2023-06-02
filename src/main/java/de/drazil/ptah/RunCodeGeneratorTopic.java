package de.drazil.ptah;

import com.intellij.util.messages.Topic;

public interface RunCodeGeneratorTopic {

    Topic<RunCodeGeneratorTopic> RUN_CODE_GENERATOR_TOPIC = Topic.create("Run Code Generator", RunCodeGeneratorTopic.class);

    void generateCode();

}
