package com.socialize.notifications;

import android.content.Context;
import android.content.Intent;

import com.socialize.android.ioc.IOCContainer;
import com.socialize.log.SocializeLogger;

public class SocializeC2DMReceiver extends BaseC2DMReceiver {
	
	private SocializeLogger logger;
	private NotificationContainer container;
	private C2DMCallback notificationCallback;
	private boolean initialized = false;
	
	// Must be parameterless constructor
	public SocializeC2DMReceiver() {
		super("SocializeC2DMReceiver");
		logger = new SocializeLogger();
	}

	@Override
	public void onMessage(Context context, Intent intent) {
		try {
			if(logger != null && logger.isInfoEnabled()) {
				logger.info("SocializeC2DMReceiver received message");
			}		
			if(assertInitialized()) {
				notificationCallback.onMessage(getContext(), intent.getExtras());
			}
		} 
		catch (Exception e) {
			if(logger != null) {
				logger.error("Error processing C2DM message", e);
			}
			else {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onError(Context context, String errorId) {
		if(logger != null) {
			logger.error("C2DM registration failed with error: " + errorId);
		}
	}
	
	@Override
	public void onRegistrered(Context context, String registrationId)  {
		try {
			if(assertInitialized()) {
				notificationCallback.onRegister(context, registrationId);
			}
		} 
		catch (Exception e) {
			if(logger != null) {
				logger.error("C2DM registration failed", e);
			}
			else {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onUnregistered(Context context) {
		if(assertInitialized()) {
			notificationCallback.onUnregister(context);
		}
		
		if(logger != null && logger.isInfoEnabled()) {
			logger.info("SocializeC2DMReceiver successfully unregistered");
		}
	}

	@Override
	public void onCreate() {
		
		if(logger != null && logger.isInfoEnabled()) {
			logger.info("SocializeC2DMReceiver creating..");
		}
		
		super.onCreate();
	}
	
	// So we can mock.
	protected Context getContext() {
		return this;
	}
	
	// So we can mock.	
	protected NotificationContainer newNotificationContainer() {
		return new NotificationContainer();
	}
	
	protected boolean assertInitialized() {
		if(!initialized) {
			try {
				doInit();
				initialized = true;
			} 
			catch (Exception e) {
				if(logger != null) {
					logger.error("Failed to initialize container",e);
				}
			}
		}
		
		return initialized;
	}
	
	protected void doInit() throws Exception {
		container = newNotificationContainer();
		container.onCreate(getContext());
		initBeans();
	}
	
	protected void initBeans() {
		IOCContainer ioc = container.getContainer();
		if(ioc != null) {
			logger = ioc.getBean("logger");
			notificationCallback = ioc.getBean("notificationCallback");
		}
	}

	@Override
	public void onDestroy() {
		if(container != null) {
			container.onDestroy(getContext());
		}		
		initialized = false;
		super.onDestroy();
	}
	
	/**
	 * Expert only (not documented)
	 * @return
	 */
	NotificationContainer getNotificationContainer() {
		return container;
	}
}
