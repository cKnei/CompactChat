package at.notknei.compactchat;

import at.notknei.compactchat.mixins.ChatHudA;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;

public class MessageManager {
	public void removeLastMessage(Text text, MessageMetadata metadata) {
		final ChatHud chatHud = MinecraftClient.getInstance().inGameHud.getChatHud();
		final MessageSignatureData signature = metadata.getLastSignature();

		if ( signature == null )
			((ChatHudA) chatHud).getMessages().removeIf(chatHudLine -> chatHudLine.content().equals(text));
		else chatHud.removeMessage(signature);
	}
}
