package it.cosenonjaviste.utils;

import rx.functions.Action2;
import rx.functions.Func0;

public class RxJoiner {

	public static <T> Func0<StringBuilder> builder() {
		return StringBuilder::new;
	}
	
	public static Action2<StringBuilder, String> join(String separator) {
		return (builder, value) -> {
			if (builder.length() != 0) {
				builder.append(separator);
			}
			builder.append(value);
		};
	}
}
