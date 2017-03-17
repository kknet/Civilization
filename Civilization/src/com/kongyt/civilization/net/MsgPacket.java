package com.kongyt.civilization.net;

public class MsgPacket {
	public int msgId = 0;
	public int msgLen = 0;
	public byte[] msgData = null;
	
	public byte[] toBytes(){
		byte[] rs = null;
		if(this.msgLen > 0){
			rs = new byte[this.msgLen];
			rs[0] = (byte)(this.msgId >> 24);
			rs[1] = (byte)((this.msgId >> 16)&0xFF);
			rs[2] = (byte)((this.msgId >> 8)&0xFF);
			rs[3] = (byte)(this.msgId & 0xFF);	
			rs[4] = (byte)(this.msgLen >> 24);
			rs[5] = (byte)((this.msgLen >> 16)&0xFF);
			rs[6] = (byte)((this.msgLen >> 8)&0xFF);
			rs[7] = (byte)(this.msgLen & 0xFF);	
			System.arraycopy(this.msgData, 0, rs, 8, this.msgData.length);
		}
		return rs;
	}
}
