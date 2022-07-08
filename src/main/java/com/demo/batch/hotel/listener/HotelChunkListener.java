package com.demo.batch.hotel.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component
public class HotelChunkListener implements ChunkListener {

    Logger logger = LoggerFactory.getLogger(HotelChunkListener.class);

    @Override
    public void beforeChunk(ChunkContext chunkContext) {
        logger.info("beforeChunk");
    }

    @Override
    public void afterChunk(ChunkContext chunkContext) {
        logger.info("afterChunk");
    }

    @Override
    public void afterChunkError(ChunkContext chunkContext) {
        logger.error("afterChunkError");
    }
}
