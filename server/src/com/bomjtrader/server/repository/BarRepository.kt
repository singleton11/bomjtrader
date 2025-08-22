package com.bomjtrader.server.repository

import com.bomjtrader.server.entity.BarEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface BarRepository : CoroutineCrudRepository<BarEntity, Long>
