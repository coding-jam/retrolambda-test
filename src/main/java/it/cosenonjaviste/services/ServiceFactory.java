package it.cosenonjaviste.services;

import java.util.concurrent.Executor;

import retrofit.RestAdapter;
import retrofit.RestAdapter.Builder;

public class ServiceFactory {
	
	private ServiceFactory() {
	}

	public static <T> T create(Class<T> clazz) {
		return createEndpoint().build().create(clazz);
	}
	
	public static <T> T create(Class<T> clazz, Executor httpExecutor) {
		return createEndpoint().setExecutors(httpExecutor, null).build().create(clazz);
	}

	private static Builder createEndpoint() {
		return new RestAdapter.Builder().setEndpoint("http://private-3a3138-simplearticles.apiary-mock.com");
	}
}
