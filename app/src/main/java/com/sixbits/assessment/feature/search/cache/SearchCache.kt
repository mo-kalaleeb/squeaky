package com.sixbits.assessment.feature.search.cache

import com.sixbits.assessment.feature.search.datasource.local.CacheEntry
import com.sixbits.assessment.feature.search.datasource.local.ISearchCache
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class SearchCache constructor(private val cacheDao: CacheDao) : ISearchCache {
    override fun saveCache(cacheEntry: CacheEntry): Completable =
        cacheDao.save(cacheEntry)

    override fun saveCacheList(cacheEntry: List<CacheEntry>): Completable =
        cacheDao.saveMany(cacheEntry)

    override fun getCachedRequests(): Single<List<CacheEntry>> =
        cacheDao.getCache()
}
