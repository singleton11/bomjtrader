package com.bomjtrader.server.repository

import com.bomjtrader.server.entity.AssetEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AssetRepository : CoroutineCrudRepository<AssetEntity, String>
