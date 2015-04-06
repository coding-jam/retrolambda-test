package it.cosenonjaviste.utils;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action2;
import rx.functions.Func0;

public class RxCollector {

	public static <T> Func0<List<T>> asListOf(Class<T> clazz) {
		return ArrayList<T>::new;
	}
	
	public static <T> Action2<List<T>, ? super T> collect() {
		return (list, value) -> list.add(value);
	}
}
