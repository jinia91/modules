package kr.co.external_api

import kr.co.external_api.domain.Item
import kr.co.external_api.domain.ItemRepository
import org.springframework.data.jpa.repository.JpaRepository

interface JpaItemRepositoryImpl : JpaRepository<Item, Long>, ItemRepository