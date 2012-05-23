package com.apply.b2b.cms.cache;
/**
 * 
 * @author luoweifeng
 *
 */
public interface ICacheEntry {
    /**
     * Gives access to the object within the cache entry.
     **/
    Object getCeObject();
    
    /**
     * Returns the  object identifier associated to the  object in the
     * cache entry.
     **/
    Object getCeIdentifier();
}
