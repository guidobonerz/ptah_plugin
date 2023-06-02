package de.drazil.ptah;

import com.intellij.util.messages.Topic;

public interface PtahActionTopic {

    Topic<PtahActionTopic> RUN_CODE_GENERATOR_TOPIC = Topic.create("Run Code Generator", PtahActionTopic.class);

    void generateCode();

}
