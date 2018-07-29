package com.example.distributedcache.services;

/**
 * Startup service
 */
public interface OnStartupService {

    /**
     * Method which is invoked when Application is booted to sync all entries from other server
     */
    void synchronizeAll();
}
