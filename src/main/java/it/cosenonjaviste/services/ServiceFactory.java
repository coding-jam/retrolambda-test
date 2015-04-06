package it.cosenonjaviste.services;

import retrofit.RestAdapter;

public class ServiceFactory {
	
	private ServiceFactory() {
	}

	public static <T> T create(Class<T> clazz) {
		return new RestAdapter.Builder().setEndpoint("http://private-3a3138-simplearticles.apiary-mock.com").build().create(clazz);
	}
}
