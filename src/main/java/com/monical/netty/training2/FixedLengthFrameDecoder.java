package com.monical.netty.training2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author zijie.cao
 * @date 2018-01-26 22:33:50
 */
public class FixedLengthFrameDecoder extends ByteToMessageDecoder {

    private final int fixLen;

    public FixedLengthFrameDecoder(int fixLen) {
        if (fixLen < 0)
            throw new IllegalArgumentException("fixLen must be a positive number");
        this.fixLen = fixLen;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() >= fixLen) {
            out.add(in.readBytes(fixLen));
        }

    }
}
