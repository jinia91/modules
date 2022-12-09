package kr.co.external_api.application

import kr.co.external_api.domain.Item
import kr.co.external_api.domain.ItemRepository
import kr.co.external_api.in_comming.ItemRegisterUseCase
import kr.co.external_api.in_comming.RegisterCommand
import org.springframework.stereotype.Service

@Service
class ItemRegisterService(
    private val itemRepository: ItemRepository
) : ItemRegisterUseCase {
    override fun register(command: RegisterCommand) : Long {
        val newItem = Item.newOne(command.content)
        itemRepository.save(newItem)
        return newItem.id
    }
}