package org.yoannbp.google.maps;

public enum Language {

	ar("ar", "ARABIC"), bg("bg", "BULGARIAN"), bn("bn", "BENGALI"), ca("ca",
			"CATALAN"), cs("cs", "CZECH"), da("da", "DANISH"), de("de",
			"GERMAN"), el("el", "GREEK"), en("en", "ENGLISH"), enAU("en-AU",
			"ENGLISH (AUSTRALIAN)"), enGB("en-GB", "ENGLISH (GREAT BRITAIN)"), es(
			"es", "SPANISH"), eu("eu", "BASQUE"), fa("fa", "FARSI"), fi("fi",
			"FINNISH"), fil("fil", "FILIPINO"), fr("fr", "FRENCH"), gl("gl",
			"GALICIAN"), gu("gu", "GUJARATI"), hi("hi", "HINDI"), hr("hr",
			"CROATIAN"), hu("hu", "HUNGARIAN"), id("id", "INDONESIAN"), it(
			"it", "ITALIAN"), iw("iw", "HEBREW"), ja("ja", "JAPANESE"), kn(
			"kn", "KANNADA"), ko("ko", "KOREAN"), lt("lt", "LITHUANIAN"), lv(
			"lv", "LATVIAN"), ml("ml", "MALAYALAM"), mr("mr", "MARATHI"), nl(
			"nl", "DUTCH"), nn("nn", "NORWEGIAN NYNORSK"), no("no", "NORWEGIAN"), or(
			"or", "ORIYA"), pl("pl", "POLISH"), pt("pt", "PORTUGUESE"), ptBR(
			"pt-BR", "PORTUGUESE (BRAZIL)"), ptPT("pt-PT",
			"PORTUGUESE (PORTUGAL)"), rm("rm", "ROMANSCH"), ro("ro", "ROMANIAN"), ru(
			"ru", "RUSSIAN"), sk("sk", "SLOVAK"), sl("sl", "SLOVENIAN"), sr(
			"sr", "SERBIAN"), sv("sv", "SWEDISH"), tl("tl", "TAGALOG"), ta(
			"ta", "TAMIL"), te("te", "TELUGU"), th("th", "THAI"), tr("tr",
			"TURKISH"), uk("uk", "UKRAINIAN"), vi("vi", "VIETNAMESE"), zhCn(
			"zh-CN", "CHINESE (SIMPLIFIED)"), zhTw("zh-TW",
			"CHINESE (TRADITIONAL)");

	private String code;
	private String name;

	private Language(String code, String name) {
		this.code = code;
		this.name = name;

	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}
