package com.ugaoxin.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;
 
/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: RetryLimitHashedCredentialsMatcher.java
 * @Prject: ugaoxin_yun
 * @Package: com.ugaoxin.shiro
 * @Description: TODO
 * @author: Array老师 
 * @version: V1.0  
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

	private Cache<String, AtomicInteger> passwordRetryCache;

	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		 
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		if (retryCount.incrementAndGet() > 5) {
		 
			throw new ExcessiveAttemptsException();
		}

		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
		 
			passwordRetryCache.remove(username);
		}
		return matches;
	}
}