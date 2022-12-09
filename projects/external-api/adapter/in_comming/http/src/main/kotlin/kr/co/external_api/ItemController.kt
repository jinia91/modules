package kr.co.external_api

import kr.co.external_api.in_comming.ItemRegisterUseCase
import kr.co.external_api.in_comming.RegisterCommand
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController(
    private val registerUseCase: ItemRegisterUseCase
){

    @GetMapping("/items")
    fun register(){
        registerUseCase.register(RegisterCommand("item"))
    }
}