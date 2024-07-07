package at.notknei.compactchat;

import net.minecraft.network.message.MessageSignatureData;
import org.jetbrains.annotations.Nullable;

public class MessageMetadata {
	private int count = 1;
	private int lastSeen;
	@Nullable
	private MessageSignatureData lastSignature;

	public MessageMetadata(@Nullable MessageSignatureData signature, int lastSeen) {
		this.lastSignature = signature;
		this.lastSeen = lastSeen;
	}

	public int getLastSeen() {
		return this.lastSeen;
	}

	public int getCount() {
		return this.count;
	}

	public void updateMetadata(int seenAt, @Nullable MessageSignatureData signatureData) {
		this.count++;
		this.lastSeen = seenAt;
		this.lastSignature = signatureData;
	}

	@Nullable
	public MessageSignatureData getLastSignature() {
		return this.lastSignature;
	}
}
