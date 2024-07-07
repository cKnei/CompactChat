package at.notknei.compactchat;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ConcurrentHashMap;

public class CompactChat {
	private static final ConcurrentHashMap<Integer, MessageMetadata> MessageMap = new ConcurrentHashMap<>();
	private static final MessageManager messageManager = new MessageManager();
	private static final MessageFormatter messageFormatter = new MessageFormatter();
	private static boolean CLEANING_MAP = false;

	private CompactChat() {
	}

	public static Text addMessage(Text text, @Nullable MessageSignatureData signatureData, int creationTick) {
		int hash = text.hashCode();
		if ( !MessageMap.containsKey(hash) ) {
			MessageMap.put(hash, new MessageMetadata(signatureData, creationTick));
			return text;
		}

		MessageMetadata metadata = MessageMap.get(hash);
		messageManager.removeLastMessage(messageFormatter.appendCount(text, metadata), metadata);
		MinecraftClient.getInstance().inGameHud.getChatHud().reset();
		metadata.updateMetadata(creationTick, signatureData);
		cleanMap(creationTick);
		return messageFormatter.appendCount(text, metadata);
	}

	public static void cleanMap(int approximateCurrentTick) {
		if ( CLEANING_MAP ) return;
		CLEANING_MAP = true;

		for ( int key : MessageMap.keySet() )
			// Maybe allow the max message age to be modifiable by user
			if ( approximateCurrentTick - MessageMap.get(key).getLastSeen() >= 3000 ) MessageMap.remove(key);

		CLEANING_MAP = false;
	}
}
