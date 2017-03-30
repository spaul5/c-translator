package cs345.cdecl;

public class Tool {

	public static String translate(String cdeclText) {
		return EnglishGenerator.generate(cdeclText);
	}
}
