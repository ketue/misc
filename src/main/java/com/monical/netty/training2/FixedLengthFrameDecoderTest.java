package com.monical.netty.training2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zijie.cao
 * @date 2018-01-26 22:38:29
 */
public class FixedLengthFrameDecoderTest {

    @Test
    public void testFrameDecoded() {
        ByteBuf byteBuf = Unpooled.buffer();
        for (int i = 0; i < 12; i++) {
            byteBuf.writeByte(i);

        }
        ByteBuf buf = byteBuf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        channel.writeInbound(buf.readBytes(2));
        channel.writeInbound(buf.readBytes(7));

        // channel.finish();
        ByteBuf read = (ByteBuf) channel.readInbound();
        Assert.assertEquals(byteBuf.readSlice(3), read);
        read.release();

        // for(int i = 0; i< read.readableBytes(); i++) {
        //     System.out.println(read.getByte(i));
        // }
        // System.out.println(read.writableBytes() );
        read = (ByteBuf) channel.readInbound();
        Assert.assertEquals(byteBuf.readSlice(3), read);
        read.release();

        read = (ByteBuf) channel.readInbound();
        Assert.assertEquals(byteBuf.readSlice(3), read);
        read.release();
        channel.writeInbound(buf.readBytes(3));
        System.out.println(channel.readInbound());
        read = (ByteBuf) channel.readInbound();
        // Assert.assertNull(channel.readInbound());
        System.out.println(channel.readInbound());
        buf.release();




    }
}
