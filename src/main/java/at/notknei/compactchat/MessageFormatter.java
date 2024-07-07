package at.notknei.compactchat;

import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.text.DecimalFormat;

public class MessageFormatter {
	private static final DecimalFormat DIGIT_FORMAT = new DecimalFormat("###,###");
	private static final Style TEXT_STYLE = Style.EMPTY.withColor(Formatting.GRAY).withBold(false).withItalic(false).withUnderline(false).withStrikethrough(false);

	public Text appendCount(Text message, MessageMetadata metadata) {
		final int count = metadata.getCount();

		if ( count == 1 ) return message;

		return message.copy().append(Text.literal(" (" + DIGIT_FORMAT.format(count) + ")").setStyle(TEXT_STYLE));
	}
}