package kr.co.external_api.domain

interface ItemRepository {
    fun save(item: Item)
}