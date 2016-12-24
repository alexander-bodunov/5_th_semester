/*
 * Created on 08.05.2005
 * 
 * Copyright BMSTU 2005
 */
package ru.bmstu.iu5.opsk;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Egorova Olga
 */
public class Messages {

	private static final String BUNDLE_NAME = "ru.bmstu.iu5.opsk.messages";//$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	public static String getString(String key) {
		// TODO Auto-generated method stub
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
