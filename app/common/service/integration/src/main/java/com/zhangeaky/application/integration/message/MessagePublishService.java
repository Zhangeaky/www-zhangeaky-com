package com.zhangeaky.application.integration.message;

public interface MessagePublishService {

    boolean publish(String payload);

    default boolean AsyncPublish() {
        return true;
    }
}
